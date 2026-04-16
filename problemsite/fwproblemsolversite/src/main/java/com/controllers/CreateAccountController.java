package com.controllers;

import java.io.IOException;
import java.util.UUID;

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
    public void initialize(){
        accountTypeBox.getItems().addAll(
            "STUDENT",
            "ADMIN",
            "CONTRIBUTOR"
        );
    }

    @FXML
    private void handleCreateAccount(javafx.event.ActionEvent event) throws IOException {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String accountType = accountTypeBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || 
            username.isEmpty() || email.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty() || accountType == null) {

            errorLabel.setText("All fields are required.");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
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
            firstName,
            lastName
        );
        
        newAccount.setAccountType(
            com.fwproblemsolversite.enums.AccountType.valueOf(accountType)
        );
        AccountData.getInstance().getAccounts().add(newAccount);

        com.fwproblemsolversite.io.dataWriter.saveAccounts(
            AccountData.getInstance().getAccounts()
        );

        App.setCurrentUser(newAccount);
        App.setRoot("dashboard");
    }

   
    @FXML
    private void goToLogin(javafx.event.ActionEvent event) throws IOException {
        App.setRoot("login");
    }
}