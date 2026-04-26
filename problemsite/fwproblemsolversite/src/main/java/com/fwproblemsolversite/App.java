package com.fwproblemsolversite;

import java.io.IOException;

import com.fwproblemsolversite.io.dataLoader;

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
    private static javafx.application.HostServices hostServices;

    @Override
    public void start(Stage stage) throws IOException {
        hostServices = getHostServices();
        dataLoader loader = new dataLoader();
        ProblemApplication problemApp = ProblemApplication.getInstance(); //this will load all data.
        scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static javafx.application.HostServices getHostServicesInstance() {
        return hostServices;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }

}