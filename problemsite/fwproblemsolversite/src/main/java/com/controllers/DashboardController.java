package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import javafx.scene.control.Label;

public class DashboardController {
    
    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        Account currentUser = App.getCurrentUser();
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getFirstName() + "!");
        } else {
            welcomeLabel.setText("Welcome!");
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        App.setCurrentUser(null);
        App.setRoot("home");
    }

}
