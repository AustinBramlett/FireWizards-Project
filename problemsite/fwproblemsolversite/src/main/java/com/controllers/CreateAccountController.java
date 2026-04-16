package com.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {
    @FXML 
    private TextField firstNameField;

    @FXML 
    private TextField lastNameField;

    @FXML 
    private PasswordField confirmPasswordField;

    @FXML 
    private ComboBox<String> accountTypeBox;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleCreateAccount(javafx.event.ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields are required.");
            return;
        }

        for (Account acc : AccountData.getInstance().getAccounts()) {
            if (acc.getUsername().equalsIgnoreCase(username) ||
                acc.getEmail().equalsIgnoreCase(email)) {
                errorLabel.setText("Username or email already exists.");
                return;
            }
        }

        Account newAccount = new Account(
            UUID.randomUUID(),
            username,
            email,
            password,
            "",
            ""
        );

        AccountData.getInstance().getAccounts().add(newAccount);

        saveAccountsToJSON();

        App.setCurrentUser(newAccount);
        App.setRoot("dashboard");
    }

    @SuppressWarnings("unchecked")
    private void saveAccountsToJSON() {
        JSONArray accountArray = new JSONArray();

        for (Account acc : AccountData.getInstance().getAccounts()) {
            JSONObject obj = new JSONObject();

            obj.put("id", acc.getId().toString());
            obj.put("username", acc.getUsername());
            obj.put("email", acc.getEmail());
            obj.put("password", acc.getPassword());
            obj.put("firstName", acc.getFirstName());
            obj.put("lastName", acc.getLastName());

            accountArray.add(obj);
        }

        try (FileWriter file = new FileWriter("accounts.json")) {
            file.write(accountArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin(javafx.event.ActionEvent event) throws IOException {
        App.setRoot("login");
    }
}