package com.controllers;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.problems.Problem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProblemController {

    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label difficultyLabel;
    @FXML private HBox tagsBox;
    @FXML private VBox examplesBox;
    @FXML private TextArea codeArea;
    @FXML private TextArea inputArea;

    @FXML
    public void initialize() {
        Problem problem = App.getCurrentProblem();

        if (problem != null) {
            titleLabel.setText(problem.getTitle());
            descriptionLabel.setText(problem.getDescription());

            //Difficulty 
            String difficulty = problem.getDifficulty().toString();
            difficultyLabel.setText(difficulty);

            switch (difficulty) {
                case "EASY":
                     difficultyLabel.setStyle("-fx-background-color: #2ecc71;" +
                                             "-fx-text-fill: white;" +
                                             "-fx-padding: 4 10;" +
                                             "-fx-background-radius: 12;"
            );
                    break;
                    
                case "MEDIUM":
                     difficultyLabel.setStyle("-fx-background-color: #f1c40f;" +
                                             "-fx-text-fill: black;" +
                                             "-fx-padding: 4 10;" +
                                             "-fx-background-radius: 12;"
            );
                    break;
                case "HARD":
                     difficultyLabel.setStyle("-fx-background-color: #e74c3c;" +
                                             "-fx-text-fill: white;" +
                                             "-fx-padding: 4 10;" +
                                             "-fx-background-radius: 12;"
            );
                    break;   
            }

            //Tags
            tagsBox.getChildren().clear();

            for (String tag : problem.getTags()) {
                Label tagLabel = new Label(tag);

                tagLabel.setStyle("-fx-background-color: #3498db;" +
                                  "-fx-text-fill: white;" +
                                  "-fx-padding: 4 10;" +
                                  "-fx-background-radius: 10;");

                tagsBox.getChildren().add(tagLabel);
            }

            //Examples
            examplesBox.getChildren().clear();

            if(problem.getExamples() != null){
                for (int i = 0; i < problem.getExamples().size(); i++){

                    String input = problem.getExamples().get(i).get(0);
                    String output = problem.getExamples().get(i).get(1);

                    Label exampleLabel = new Label(
                        "Example " + (i + 1) + ":\n" +
                        "Input: " + input + "\n" +
                        "Output: " + output
                    );

                    exampleLabel.setStyle("-fx-background-color: #2a2a2a; " +
                                          "-fx-background-radius: 8; " + 
                                          "-fx-padding: 12;" +
                                          "-fx-text-fill: white;");

                    exampleLabel.setWrapText(true);
                    examplesBox.getChildren().add(exampleLabel);
                }
            }
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