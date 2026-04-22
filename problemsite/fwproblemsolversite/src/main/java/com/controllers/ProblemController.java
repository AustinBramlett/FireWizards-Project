package com.controllers;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.problems.Problem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProblemController {

    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;

    @FXML
    public void initialize() {
        Problem problem = App.getCurrentProblem();

        if (problem != null) {
            titleLabel.setText(problem.getTitle());
            descriptionLabel.setText(problem.getDescription());
        }
    }

    @FXML
    private void handleBackToProblems() {
        try {
            App.setRoot("problems");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}