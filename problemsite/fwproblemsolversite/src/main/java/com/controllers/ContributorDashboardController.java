package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;   
import com.fwproblemsolversite.App;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.data.Progress;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class ContributorDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label problemsSolvedLabel;
    @FXML private Label dailyStreakLabel;
    @FXML private Label totalPointsLabel;
    @FXML private Label continueProblemTitle;
    @FXML private Label continueProblemInfo;
    @FXML private Label dailyProblemTitle;
    @FXML private Label dailyProblemInfo;
    @FXML private MenuButton userMenu;
    @FXML public void initialize(){
        loadUserInfo();
        loadUserStats();
        loadProblems();
    }

    private void loadUserInfo() {
        Account currentUser = App.getCurrentUser();
        if (currentUser != null) {
            String firstName = currentUser.getFirstName();
            welcomeLabel.setText("Welcome Back, " + firstName);
            userMenu.setText(firstName + " ");
        } else {
            welcomeLabel.setText("Welcome Back, user");
            userMenu.setText("user ");
        }
    }

     private void loadUserStats() {
        Account currentUser = App.getCurrentUser();

        if (currentUser == null) {
            return;
        }

        Progress progress = currentUser.getProgress();

        if (progress == null) {
            return;
        }

        problemsSolvedLabel.setText(String.valueOf(progress.getProblemsSolved()));
        dailyStreakLabel.setText(String.valueOf(progress.getDailyStreak()));
        totalPointsLabel.setText(String.valueOf(progress.getTotalPoints()));
    }

    private void loadProblems() {
        ArrayList<Problem> problems = ProblemData.getInstance().getProblems();

        if (problems == null || problems.isEmpty()) {
            continueProblemTitle.setText("No problems loaded");
            continueProblemInfo.setText("Check problems.json");
            dailyProblemTitle.setText("No problems loaded");
            dailyProblemInfo.setText("Check problems.json");
            return;
        }

        Problem continueProblem = problems.get(0);
        continueProblemTitle.setText(getProblemTitle(continueProblem));
        continueProblemInfo.setText(buildProblemInfo(continueProblem));

        if (problems.size() > 1) {
            Problem dailyProblem = problems.get(1);
            dailyProblemTitle.setText(getProblemTitle(dailyProblem));
            dailyProblemInfo.setText(buildProblemInfo(dailyProblem));
        } else {
            dailyProblemTitle.setText(getProblemTitle(continueProblem));
            dailyProblemInfo.setText(buildProblemInfo(continueProblem));
        }
    }

     private String getProblemTitle(Problem problem) {
        return problem.getTitle();
    }

    private String buildProblemInfo(Problem problem) {
        String difficulty = String.valueOf(problem.getDifficulty());
        String tag = String.valueOf(problem.getTags());
        String description = problem.getDescription();

        return difficulty + "   " + tag + "   " + description;
    }

    @FXML
    private void handleGoToProblems() {
        System.out.println("Go to problems clicked");
    }

    @FXML
    private void handleCreateProblem() {
        System.out.println("Create problem clicked");
        // App.setRoot("createproblem");
    }
    @FXML
    private void handleProfileSettings() {
        System.out.println("Profile & Settings clicked");
        // App.setRoot("profile");
    }

    @FXML
    private void handleLogout() throws IOException {
        App.setCurrentUser(null);
        App.setRoot("login");
    }

}

 

