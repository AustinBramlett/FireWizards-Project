package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import com.fwproblemsolversite.App;

public class HomeController {

    @FXML private VBox welcomeBox;
    @FXML private VBox readmeBox;

    @FXML
    private void handleLearnMore() {
        welcomeBox.setVisible(false);
        welcomeBox.setManaged(false);

        readmeBox.setVisible(true);
        readmeBox.setManaged(true);
    }

    @FXML
    private void handleBackToWelcome() {
        readmeBox.setVisible(false);
        readmeBox.setManaged(false);

        welcomeBox.setVisible(true);
        welcomeBox.setManaged(true);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login");
    }
    
}
