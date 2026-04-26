package com.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fwproblemsolversite.App;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class CreateProblemController {

    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField summaryField;

    @FXML private ComboBox<String> difficultyBox;
    @FXML private ComboBox<String> typeBox;
    @FXML private ComboBox<String> timerBox;

    @FXML private TextField tagField;
    @FXML private TextField constraintField;
    @FXML private TextField exampleOutputField;
    @FXML private TextField noteField;

    @FXML private HBox tagsBox;
    @FXML private VBox constraintsBox;
    @FXML private VBox exampleOutputsBox;
    @FXML private VBox notesBox;
    @FXML private VBox answersBox;

    @FXML private TextField answerTitleField;
    @FXML private TextField timeComplexityField;
    @FXML private TextArea answerDescriptionArea;

    @FXML private Label messageLabel;
    @FXML private Label userNameLabel;

    private File selectedFile;

    private final ArrayList<String> tags = new ArrayList<>();
    private final ArrayList<String> constraints = new ArrayList<>();
    private final ArrayList<String> exampleOutputs = new ArrayList<>();
    private final ArrayList<String> notes = new ArrayList<>();
    private final ArrayList<String> answers = new ArrayList<>();

    @FXML
    public void initialize() {
        difficultyBox.setValue("Easy");
        typeBox.setValue("String");
        timerBox.setValue("None");

        if (userNameLabel != null) {
            userNameLabel.setText("Contributor");
        }        
    }

    @FXML
    private void handleBackToDashboard() throws IOException {
        App.setRoot("contributorDashboard");
    }

    @FXML
    private void handleCancelCreation() throws IOException {
        App.setRoot("contributorDashboard");
    }

    @FXML
    private void handleAddTag() {
        addItem(tagField, tags, tagsBox);
    }

    @FXML
    private void handleAddConstraint() {
        addItem(constraintField, constraints, constraintsBox);
    }

    @FXML
    private void handleAddExampleOutput() {
        addItem(exampleOutputField, exampleOutputs, exampleOutputsBox);
    }

    @FXML
    private void handleAddNote() {
        addItem(noteField, notes, notesBox);
    }

    private void addItem(TextField field, ArrayList<String> list, Pane box) {
        if (field == null || box == null) {
            return;
        }

        String text = field.getText().trim();

        if (text.isEmpty()) {
            return;
        }

        list.add(text);

        Label itemLabel = new Label(text);
        itemLabel.getStyleClass().add("tag-pill");

        box.getChildren().add(itemLabel);
        field.clear();

        if (messageLabel != null) {
            messageLabel.setText("");
        }
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Solution File");

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null && messageLabel != null) {
            messageLabel.setText("Selected file: " + selectedFile.getName());
        }
    }

    @FXML
    private void handleAddAnswer() {
        String title = answerTitleField.getText().trim();
        String complexity = timeComplexityField.getText().trim();
        String expectedOutput = answerDescriptionArea.getText().trim();

        if (title.isEmpty()) {
            messageLabel.setText("Enter an answer title.");
            return;
        }

        if (complexity.isEmpty()) {
            messageLabel.setText("Enter the time complexity.");
            return;
        }

        if (expectedOutput.isEmpty()) {
            messageLabel.setText("Enter the expected output.");
            return;
        }

        String answerText = title + " | " + complexity + " | " + expectedOutput;

        if (selectedFile != null) {
            answerText += " | File: " + selectedFile.getName();
        }

        answers.add(answerText);

        Label answerLabel = new Label(title + " (" + complexity + ")");
        answerLabel.getStyleClass().add("tag-pill");
        answersBox.getChildren().add(answerLabel);

        answerTitleField.clear();
        timeComplexityField.clear();
        answerDescriptionArea.clear();
        selectedFile = null;

        messageLabel.setText("Answer added.");
    }

    @FXML
    private void handleCreateProblem() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String summary = summaryField.getText().trim();

        if (title.isEmpty()) {
            messageLabel.setText("Please enter a problem title.");
            return;
        }

        if (description.isEmpty()) {
            messageLabel.setText("Please enter a description.");
            return;
        }

        if (summary.isEmpty()) {
            messageLabel.setText("Please enter a summary.");
            return;
        }

        if (tags.isEmpty()) {
            messageLabel.setText("Please add at least one tag.");
            return;
        }

        if (answers.isEmpty()) {
            messageLabel.setText("Please add at least one answer.");
            return;
        }

        messageLabel.setText("Problem created successfully!");

        clearForm();
    }

    private void clearForm() {
        titleField.clear();
        descriptionArea.clear();
        summaryField.clear();

        tagField.clear();
        constraintField.clear();
        exampleOutputField.clear();
        noteField.clear();

        answerTitleField.clear();
        timeComplexityField.clear();
        answerDescriptionArea.clear();

        tags.clear();
        constraints.clear();
        exampleOutputs.clear();
        notes.clear();
        answers.clear();

        tagsBox.getChildren().clear();
        constraintsBox.getChildren().clear();
        exampleOutputsBox.getChildren().clear();
        notesBox.getChildren().clear();
        answersBox.getChildren().clear();

        selectedFile = null;

        difficultyBox.setValue("Easy");
        typeBox.setValue("String");
        timerBox.setValue("None");
    }
}