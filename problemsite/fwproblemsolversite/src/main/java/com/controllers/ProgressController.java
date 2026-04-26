package com.controllers;

import java.util.ArrayList;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.accounts.Contributor;
import com.fwproblemsolversite.data.Achievement;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.data.Progress;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class ProgressController {

    private ProblemApplication problemApp = ProblemApplication.getInstance();

    @FXML
    private Label problemsSolvedLabel;
    @FXML
    private Label streakLabel;
    @FXML
    private Label pointsLabel;
    @FXML
    private Label streakBigLabel;

    @FXML
    private ProgressBar easyBar;
    @FXML
    private ProgressBar mediumBar;
    @FXML
    private ProgressBar hardBar;

    @FXML
    private FlowPane achievementsBox;

    @FXML
    private Label problemsSolvedTitle;
    @FXML
    private Label streakTitle;
    @FXML
    private Label pointsTitle;
    @FXML
    private Label bigStatTitle;
    @FXML
    private Label bigStatSubtext;

    @FXML
    public void initialize() {
        Account user = problemApp.getCurrentUser();
        if (user == null) {
            return;
        }

        if (user instanceof Contributor) {
            loadContributorStats((Contributor) user);
        } else {
            loadStudentStats(user);
        }
    }

    private void loadStudentStats(Account user) {

        Progress progress = user.getProgress();
        if (progress == null) {
            return;
        }

        problemsSolvedLabel.setText(String.valueOf(progress.getProblemsSolved()));
        streakLabel.setText(String.valueOf(progress.getDailyStreak()));
        pointsLabel.setText(String.valueOf(progress.getTotalPoints()));
        streakBigLabel.setText(String.valueOf(progress.getDailyStreak()));

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

        achievementsBox.getChildren().clear();

        ArrayList<Achievement> achievements = generateStudentAchievements(progress);

        if (achievements.isEmpty()) {

            VBox card = new VBox();
            card.setSpacing(10);
            card.getStyleClass().add("stat-card");

            Label text = new Label("No achievements yet");
            text.setStyle("-fx-text-fill: #b0b0b0;");

            card.getChildren().add(text);
            achievementsBox.getChildren().add(card);
            return;
        }

        for (Achievement a : achievements) {
            VBox card = createAchievementCard(a.getTitle(), a.getDescription());
            achievementsBox.getChildren().add(card);
        }
    }

    private void loadContributorStats(Contributor contributor) {

        int created = contributor.getQuestionsMade().size();

        problemsSolvedLabel.setText(String.valueOf(created));
        streakLabel.setText("0");
        pointsLabel.setText("0");
        streakBigLabel.setText(String.valueOf(created));

        problemsSolvedTitle.setText("Problems Created");
        streakTitle.setText("Approved");
        pointsTitle.setText("Upvotes");

        bigStatTitle.setText("Problems Created");
        bigStatSubtext.setText("total created");

        int easy = 0, medium = 0, hard = 0;

        var problems = ProblemData.getInstance().getProblems();

        for (var p : problems) {
            if (contributor.getQuestionsMade().contains(p.getID())) {
                switch (p.getDifficulty().toString()) {
                    case "EASY":
                        easy++;
                        break;
                    case "MEDIUM":
                        medium++;
                        break;
                    case "HARD":
                        hard++;
                        break;
                }
            }
        }

        int total = easy + medium + hard;

        if (total > 0) {
            easyBar.setProgress((double) easy / total);
            mediumBar.setProgress((double) medium / total);
            hardBar.setProgress((double) hard / total);
        } else {
            easyBar.setProgress(0);
            mediumBar.setProgress(0);
            hardBar.setProgress(0);
        }

        achievementsBox.getChildren().clear();

        if (created == 0) {

            VBox card = new VBox();
            card.setSpacing(10);
            card.getStyleClass().add("stat-card");

            Label text = new Label("No contributions yet");
            text.setStyle("-fx-text-fill: #b0b0b0;");

            card.getChildren().add(text);
            achievementsBox.getChildren().add(card);
            return;
        }

        achievementsBox.getChildren().add(
                createAchievementCard("First Problem Created", "You created your first problem!")
        );

        if (created >= 3) {
            achievementsBox.getChildren().add(
                    createAchievementCard("3 Problems Created", "Nice progress!")
            );
        }

        if (created >= 5) {
            achievementsBox.getChildren().add(
                    createAchievementCard("5 Problems Created", "Keep contributing!")
            );
        }

        if (created >= 10) {
            achievementsBox.getChildren().add(
                    createAchievementCard("10 Problems Created", "You're a top contributor!")
            );
        }
    }

    private ArrayList<Achievement> generateStudentAchievements(Progress progress) {

        ArrayList<Achievement> list = new ArrayList<>();

        int solved = progress.getProblemsSolved();
        int streak = progress.getDailyStreak();

        if (solved >= 1) {
            Achievement a = new Achievement(null);
            a.setTitle("First Problem Solved");
            a.setDescription("You solved your first problem!");
            list.add(a);
        }

        if (solved >= 5) {
            Achievement a = new Achievement(null);
            a.setTitle("5 Problems Solved");
            a.setDescription("Nice work!");
            list.add(a);
        }

        if (solved >= 10) {
            Achievement a = new Achievement(null);
            a.setTitle("10 Problems Solved");
            a.setDescription("You're getting strong!");
            list.add(a);
        }

        if (streak >= 3) {
            Achievement a = new Achievement(null);
            a.setTitle("3 Day Streak");
            a.setDescription("Consistency matters!");
            list.add(a);
        }

        if (streak >= 7) {
            Achievement a = new Achievement(null);
            a.setTitle("7 Day Streak");
            a.setDescription("You're on fire 🔥");
            list.add(a);
        }

        return list;
    }

    private VBox createAchievementCard(String titleText, String descText) {

        VBox card = new VBox();
        card.setSpacing(10);
        card.setPrefWidth(200);
        card.getStyleClass().add("stat-card");

        Label title = new Label(titleText);
        Label desc = new Label(descText);

        title.setStyle("-fx-text-fill: white; -fx-font-size: 14;");
        desc.setStyle("-fx-text-fill: #b0b0b0; -fx-font-size: 12;");

        card.getChildren().addAll(title, desc);
        return card;
    }

    @FXML
    private void handleBackToDashboard() {
        try {
            Account user = problemApp.getCurrentUser();
            if (user instanceof Contributor) {
                App.setRoot("contributorDashboard");
            } else {
                App.setRoot("dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
