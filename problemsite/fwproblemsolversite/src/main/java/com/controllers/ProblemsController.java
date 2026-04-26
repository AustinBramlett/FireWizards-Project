package com.controllers;

import java.util.ArrayList;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.problems.Problem;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProblemsController {

    private ProblemApplication problemApp = ProblemApplication.getInstance();
    @FXML
    private VBox problemsContainer;
    @FXML
    private TextField searchField;

    private String currentFilter = "ALL";

    @FXML
    public void initialize() {
        updateProblemList();
    }

    private void updateProblemList() {

        problemsContainer.getChildren().clear();

        ArrayList<Problem> problems = ProblemData.getInstance().getProblems();

        String query = searchField.getText().toLowerCase();

        for (Problem problem : problems) {

            boolean matchesDifficulty
                    = currentFilter.equals("ALL")
                    || problem.getDifficulty().toString().equals(currentFilter);

            boolean matchesSearch
                    = problem.getTitle().toLowerCase().contains(query);

            if (matchesDifficulty && matchesSearch) {
                problemsContainer.getChildren().add(createProblemCard(problem));
            }
        }

        if (problemsContainer.getChildren().isEmpty()) {
            Label empty = new Label("No problems found.");
            empty.setStyle("-fx-text-fill: gray;");
            problemsContainer.getChildren().add(empty);
        }
    }

    private VBox createProblemCard(Problem problem) {
        VBox card = new VBox();
        card.setSpacing(6);
        card.getStyleClass().add("problem-card");

        HBox topRow = new HBox();
        topRow.setSpacing(10);
        topRow.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(problem.getTitle());
        title.getStyleClass().add("problem-title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label difficulty = new Label(problem.getDifficulty().toString());

        String diff = problem.getDifficulty().toString();

        difficulty.getStyleClass().add("difficulty");

        if (diff.equals("EASY")) {
            difficulty.getStyleClass().add("easy");
        } else if (diff.equals("MEDIUM")) {
            difficulty.getStyleClass().add("medium");
        } else if (diff.equals("HARD")) {
            difficulty.getStyleClass().add("hard");
        }

        Label type = new Label(problem.getType().toString());
        type.getStyleClass().add("type-pill");

        topRow.getChildren().addAll(title, spacer, difficulty, type);

        Label description = new Label(problem.getDescription());
        description.getStyleClass().add("problem-subtext");
        description.setWrapText(true);

        card.getChildren().addAll(topRow, description);

        card.setOnMouseClicked(e -> {
            System.out.println("Clicked on problem: " + problem.getTitle());

            try {
                problemApp.setCurrentProblem(problem);
                App.setRoot("problem");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return card;
    }

    @FXML
    private void handleBackToProblems() {
        try {
            App.setRoot("problems");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard() {
        try {
            Account user = problemApp.getCurrentUser();
            if (user != null && user.getAccountType().toString().equals("CONTRIBUTOR")) {
                App.setRoot("contributorDashboard");
            } else {
                App.setRoot("dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowAll() {
        currentFilter = "ALL";
        updateProblemList();
    }

    @FXML
    private void handleShowEasy() {
        currentFilter = "EASY";
        updateProblemList();
    }

    @FXML
    private void handleShowMedium() {
        currentFilter = "MEDIUM";
        updateProblemList();
    }

    @FXML
    private void handleShowHard() {
        currentFilter = "HARD";
        updateProblemList();
    }

    @FXML
    private void handleSearch() {
        updateProblemList();
    }
}
