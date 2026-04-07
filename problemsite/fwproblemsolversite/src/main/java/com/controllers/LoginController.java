package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;
    
    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        Account account = AccountData.getInstance().getAccountByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            App.setCurrentUser(account);
            App.setRoot("dashboard");
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
}

        
