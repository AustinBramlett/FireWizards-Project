package com.controllers;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.Achievement;
import com.fwproblemsolversite.data.Progress;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class ProgressController {

    @FXML private Label problemsSolvedLabel;
    @FXML private Label streakLabel;
    @FXML private Label pointsLabel;
    @FXML private Label streakBigLabel;

    @FXML private ProgressBar easyBar;
    @FXML private ProgressBar mediumBar;
    @FXML private ProgressBar hardBar;

    @FXML private FlowPane achievementsBox;

    @FXML
    public void initialize() {

        Account user = App.getCurrentUser();
        if (user == null) return;

        Progress progress = user.getProgress();
        if (progress == null) return;

        // Top stats
        problemsSolvedLabel.setText(String.valueOf(progress.getProblemsSolved()));
        streakLabel.setText(String.valueOf(progress.getDailyStreak()));
        pointsLabel.setText(String.valueOf(progress.getTotalPoints()));

        // Big streak (right card)
        streakBigLabel.setText(String.valueOf(progress.getDailyStreak()));

        // Difficulty bars
        int total = progress.getProblemsSolved();

        if (total > 0) {
            easyBar.setProgress((double) progress.getEasySolved() / total);
            mediumBar.setProgress((double) progress.getMediumSolved() / total);
            hardBar.setProgress((double) progress.getHardSolved() / total);
        } else {
            easyBar.setProgress(0);
            mediumBar.setProgress(0);
            hardBar.setProgress(0);
        }

        // Achievements
        achievementsBox.getChildren().clear();

        if (progress.getAchievements() == null || progress.getAchievements().isEmpty()) {

            VBox card = new VBox();
            card.setSpacing(10);
            card.getStyleClass().add("stat-card");

            Label text = new Label("No achievements yet");
            text.setStyle("-fx-text-fill: #b0b0b0;");

            card.getChildren().add(text);
            achievementsBox.getChildren().add(card);
            return;
        }

        for (Achievement a : progress.getAchievements()) {

            VBox card = new VBox();
            card.setSpacing(10);
            card.setPrefWidth(200);
            card.getStyleClass().add("stat-card");

            Label title = new Label(a.getTitle());
            Label desc = new Label(a.getDescription());

            title.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
            desc.setStyle("-fx-text-fill: #b0b0b0; -fx-font-size: 12;");

            card.getChildren().addAll(title, desc);
            achievementsBox.getChildren().add(card);
        }
    }
    @FXML
    private void handleBackToDashboard() {
        try {
            Account user = App.getCurrentUser();
            if (user != null && user.getAccountType().toString().equals("CONTRIBUTOR")) {
                App.setRoot("contributorDashboard");
            } else {
                App.setRoot("dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
