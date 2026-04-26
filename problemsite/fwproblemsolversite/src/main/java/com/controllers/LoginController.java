package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.ProblemApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    private ProblemApplication problemApp = ProblemApplication.getInstance();
    private ArrayList<Account> accounts = problemApp.getAccountData().getAccounts();
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
        loginInput = getUsernameByInput(loginInput);
        Account account = problemApp.login(loginInput, password);
        if (account != null) {
            if (account.getAccountType() == AccountType.CONTRIBUTOR) {
                App.setRoot("contributordashboard");
            } else {
                App.setRoot("dashboard");
            }
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
    private String getUsernameByInput(String loginInput) {

        for (Account account : accounts) {
            if (account.getUsername().equalsIgnoreCase(loginInput) || account.getEmail().equalsIgnoreCase(loginInput)) {
                return account.getUsername();
            }
        }
        return null;
    }

    @FXML
    private void handleForgotPassword() throws IOException {
        App.setRoot("resetPassword");
    }
}

        
