package com.fwproblemsolversite;

import java.io.IOException;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.io.dataLoader;
import com.fwproblemsolversite.problems.Problem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX App
 * 
 * Launches the UI.
 */
public class App extends Application {

    private static Scene scene;
    private static Account currentUser;
    private static Problem currentProblem;

    @Override
    public void start(Stage stage) throws IOException {
        dataLoader loader = new dataLoader();
        AccountData.getInstance().setAccounts(dataLoader.LoadAccounts());
        ProblemData.getInstance().getProblems().addAll(dataLoader.LoadProblems());

        scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Account getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Account user) {
        currentUser = user;
    }

    public static void setCurrentProblem(Problem problem) {
        currentProblem = problem;
    }
    
    public static Problem getCurrentProblem() {
        return currentProblem;
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }

}