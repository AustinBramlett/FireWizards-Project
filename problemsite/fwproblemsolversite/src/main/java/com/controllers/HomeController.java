package com.controllers;

import java.io.IOException;

import com.fwproblemsolversite.App;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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

    @FXML
    private void openDemoVideo() {
        App.getHostServicesInstance().showDocument("https://youtu.be/qUXdicF84K4");
    }

    @FXML
    private void openUML() {
        App.getHostServicesInstance().showDocument(
            "https://lucid.app/lucidchart/590a60f9-f40d-4f81-b1f5-5562af032aed/edit"
        );
    }
}
