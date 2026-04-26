package com.controllers;

import java.io.IOException;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetPasswordController {

    @FXML private TextField usernameEmailField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;
    @FXML private Label strengthLabel;

    @FXML
    public void initialize() {
        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePasswordStrength(newValue);
        });
    }

    @FXML
    private void handleResetPassword() {
        String loginInput = usernameEmailField.getText().trim();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (loginInput.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        if (newPassword.length() < 6) {
            messageLabel.setText("Password must be at least 6 characters.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        Account account = findAccountByUsernameOrEmail(loginInput);

        if (account == null) {
            messageLabel.setText("Account not found.");
            return;
        }

        account.setPassword(newPassword);

        com.fwproblemsolversite.io.dataWriter.saveAccounts(
            AccountData.getInstance().getAccounts()
        );

        messageLabel.setText("Password updated successfully!");

        new Thread(() -> {
            try {
                Thread.sleep(1500);

                Platform.runLater(() -> {
                    try {
                        App.setRoot("login");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Account findAccountByUsernameOrEmail(String loginInput) {
        for (Account account : AccountData.getInstance().getAccounts()) {
            if (account.getUsername().equalsIgnoreCase(loginInput)
                    || account.getEmail().equalsIgnoreCase(loginInput)) {
                return account;
            }
        }

        return null;
    }

    private void updatePasswordStrength(String password) {
        if (password == null || password.isEmpty()) {
            strengthLabel.setText("");
            return;
        }

        int score = 0;

        if (password.length() >= 6) score++;
        if (password.length() >= 10) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[^a-zA-Z0-9].*")) score++;

        if (score <= 2) {
            strengthLabel.setText("Password Strength: Weak");
        } else if (score <= 4) {
            strengthLabel.setText("Password Strength: Medium");
        } else {
            strengthLabel.setText("Password Strength: Strong");
        }
    }

    @FXML
    private void handleBackToLogin() throws IOException {
        App.setRoot("login");
    }
}