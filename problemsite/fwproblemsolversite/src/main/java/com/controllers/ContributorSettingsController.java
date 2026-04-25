package com.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.Progress;
import com.fwproblemsolversite.io.dataWriter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ContributorSettingsController {

    @FXML private ImageView profileImageView;

    @FXML private Label fullNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label accountTypeLabel;
    @FXML private Label memberSinceLabel;
    @FXML private Label bioLabel;

    @FXML private Label approvedSubmissionsLabel;
    @FXML private Label problemsCreatedLabel;
    @FXML private Label pendingReviewsLabel;
    @FXML private Label totalUpvotesLabel;

    @FXML private Label streakBigLabel;
    @FXML private HBox streakDaysBox;

    @FXML private MenuButton languageMenu;
    @FXML private MenuButton difficultyMenu;
    @FXML private MenuButton autoSaveMenu;

    @FXML
    public void initialize() {
        loadUserInfo();
    }

    private void loadUserInfo() {
        Account user = App.getCurrentUser();

        if (user == null) {
            return;
        }

        fullNameLabel.setText(user.getFirstName() + " " + user.getLastName());
        emailLabel.setText(user.getEmail());
        accountTypeLabel.setText("CONTRIBUTOR");
        accountTypeLabel.getStyleClass().add("account-contributor");

        if (user.getProgress() != null) {
            Progress progress = user.getProgress();

            memberSinceLabel.setText("Member since " + progress.getLastActiveString());
            streakBigLabel.setText(String.valueOf(progress.getDailyStreak()));
            loadStreakDays(progress.getDailyStreak());
        }

        bioLabel.setText("“Create. Share. Improve”");

        approvedSubmissionsLabel.setText("0");
        problemsCreatedLabel.setText("0");
        pendingReviewsLabel.setText("0");
        totalUpvotesLabel.setText("0");
    }

    private void loadStreakDays(int streak) {
        streakDaysBox.getChildren().clear();

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int activeDays = streak % 7;

        if (streak > 0 && activeDays == 0) {
            activeDays = 7;
        }

        for (int i = 0; i < 7; i++) {
            VBox dayBox = new VBox(4);
            dayBox.setAlignment(Pos.CENTER);

            ImageView icon = new ImageView();
            icon.setFitHeight(16);
            icon.setFitWidth(16);
            icon.setPreserveRatio(true);

            if (i < activeDays) {
                icon.setImage(new Image(getClass().getResource("/com/fwproblemsolversite/images/check.png").toExternalForm()));
            } else {
                icon.setImage(new Image(getClass().getResource("/com/fwproblemsolversite/images/Ellipse 7.png").toExternalForm()));
            }

            Label dayLabel = new Label(days[i]);
            dayLabel.getStyleClass().add("streak-day-label");

            dayBox.getChildren().addAll(icon, dayLabel);
            streakDaysBox.getChildren().add(dayBox);
        }
    }

    @FXML
    private void handleHome() throws IOException {
        App.setRoot("contributorDashboard");
    }

    @FXML
    private void handleProblems() throws IOException {
        App.setRoot("problems");
    }

    @FXML
    private void handleProgress() throws IOException {
        App.setRoot("progress");
    }

    @FXML
    private void handleLogout() throws IOException {
        App.setCurrentUser(null);
        App.setRoot("login");
    }

    @FXML
    private void handleEditProfile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            profileImageView.setImage(new Image(file.toURI().toString()));
        }

        TextInputDialog dialog = new TextInputDialog(bioLabel.getText().replace("“", "").replace("”", ""));
        dialog.setTitle("Edit Profile Text");
        dialog.setHeaderText("Update your profile quote");
        dialog.setContentText("Quote:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && !result.get().trim().isEmpty()) {
            bioLabel.setText("“" + result.get().trim() + "”");
        }
    }

    @FXML
    private void handleCreateNewProblem() {
        System.out.println("Create New Problem clicked");
    }

    @FXML
    private void handleMyProblems() {
        System.out.println("My Problems clicked");
    }

    @FXML
    private void handleDrafts() {
        System.out.println("Drafts clicked");
    }

    @FXML
    private void handleReviewFeedback() {
        System.out.println("Review Feedback clicked");
    }

    @FXML private void handleLanguageJava() {
        languageMenu.setText("Java");
    }

    @FXML private void handleLanguagePython() {
        languageMenu.setText("Python");
    }

    @FXML private void handleLanguageCpp() {
        languageMenu.setText("C++");
    }

    @FXML private void handleDifficultyEasy() {
        difficultyMenu.setText("Easy");
    }

    @FXML private void handleDifficultyMedium() {
        difficultyMenu.setText("Medium");
    }

    @FXML private void handleDifficultyHard() {
        difficultyMenu.setText("Hard");
    }

    @FXML private void handleAutoSaveEnabled() {
        autoSaveMenu.setText("Enabled");
    }

    @FXML private void handleAutoSaveDisabled() {
        autoSaveMenu.setText("Disabled");
    }

    @FXML
    private void handleResetProgress() {
        Account user = App.getCurrentUser();

        if (user == null || user.getProgress() == null) {
            return;
        }

        Progress progress = user.getProgress();

        progress.setProblemsSolved(0);
        progress.setEasySolved(0);
        progress.setMediumSolved(0);
        progress.setHardSolved(0);
        progress.setTotalPoints(0);
        progress.setDailyStreak(0);
        progress.setAchievements(new java.util.ArrayList<>());

        dataWriter.saveAccounts(AccountData.getInstance().getAccounts());

        loadUserInfo();
    }

    @FXML
    private void handleDeleteAccount() throws IOException {
        Account user = App.getCurrentUser();

        if (user == null) {
            return;
        }

        AccountData.getInstance().getAccounts().remove(user);
        dataWriter.saveAccounts(AccountData.getInstance().getAccounts());

        App.setCurrentUser(null);
        App.setRoot("login");
    }
}