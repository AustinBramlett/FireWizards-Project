package com.fwproblemsolversite;

import java.io.IOException;

import javafx.fxml.FXML;
/**
 * Controller for primary view.
 * 
 * Handles user interactions for the UI
 */
public class PrimaryController {
    /**
     * Switches to a secondary view
     * 
     * @throws IOException if the view cannot be loaded
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
