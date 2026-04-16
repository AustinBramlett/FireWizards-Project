package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void goToCreateAccount() throws IOException {
    App.setRoot("create_account");
    }
    
    @FXML
    private void handleLogin() throws IOException {
        String loginInput = usernameField.getText().trim();
        String password = passwordField.getText();

        Account account = findAccountByUsernameOrEmail(loginInput);
        if (account != null && account.getPassword().equals(password)) {
            App.setCurrentUser(account);
            App.setRoot("dashboard");
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    private Account findAccountByUsernameOrEmail(String loginInput) {
        ArrayList<Account> accounts = AccountData.getInstance().getAccounts();

        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(loginInput) || account.getEmail().equalsIgnoreCase(loginInput)) {
                return account;
            }
        }
        return null;
    }
}

        
