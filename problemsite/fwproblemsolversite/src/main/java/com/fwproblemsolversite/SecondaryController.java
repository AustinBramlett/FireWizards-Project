package com.fwproblemsolversite;

import java.io.IOException;

import javafx.fxml.FXML;
/**
 * Controller for secondary view.
 * 
 * Handles user interactions for the secondary UI
 */
public class SecondaryController {
    /**
     * Switches back to the primary view
     */
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}