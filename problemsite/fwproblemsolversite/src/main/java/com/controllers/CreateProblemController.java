package com.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.io.dataWriter;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Problem;

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

    private File selectedFile;

    private final ArrayList<String> tags = new ArrayList<>();
    private final ArrayList<String> constraints = new ArrayList<>();
    private final ArrayList<String> exampleOutputs = new ArrayList<>();
    private final ArrayList<String> notes = new ArrayList<>();
    private final ArrayList<String> answerOutputs = new ArrayList<>();

    @FXML
    public void initialize() {
        difficultyBox.setValue("Easy");
        typeBox.setValue("LinkedList");
        timerBox.setValue("None");
    }

    @FXML
    private void handleBackToDashboard() throws IOException {
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
        String text = field.getText().trim();

        if (text.isEmpty()) {
            return;
        }

        list.add(text);

        Label label = new Label(text);
        label.getStyleClass().add("tag-pill");

        box.getChildren().add(label);
        field.clear();
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Solution File");

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            messageLabel.setText("Selected file: " + selectedFile.getName());
        }
    }

    @FXML
    private void handleAddAnswer() {
        String title = answerTitleField.getText().trim();
        String complexity = timeComplexityField.getText().trim();
        String expectedOutput = answerDescriptionArea.getText().trim();

        if (title.isEmpty() || complexity.isEmpty() || expectedOutput.isEmpty()) {
            messageLabel.setText("Answer title, complexity, and expected output are required.");
            return;
        }

        answerOutputs.add(expectedOutput);

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

        if (title.isEmpty() || description.isEmpty()) {
            messageLabel.setText("Title and description are required.");
            return;
        }

        if (summary.isEmpty()) {
            messageLabel.setText("Summary is required.");
            return;
        }

        if (tags.isEmpty()) {
            messageLabel.setText("Add at least one tag.");
            return;
        }

        if (answerOutputs.isEmpty()) {
            messageLabel.setText("Add at least one expected output answer.");
            return;
        }

        try {
            Difficulty difficulty = parseDifficulty(difficultyBox.getValue());
            ProblemType type = parseProblemType(typeBox.getValue());
            double timer = parseTimer(timerBox.getValue());
            Language language = Language.JAVA;

            ArrayList<ArrayList<String>> examples = buildExamples();
            ArrayList<ArrayList<String>> answers = buildAnswers();

            String fullDescription = description + "\n\nSummary: " + summary;

            Problem newProblem = new Problem(
                title,
                UUID.randomUUID(),
                fullDescription,
                constraints,
                language,
                examples,
                notes,
                type,
                tags,
                timer,
                answers,
                difficulty,
                new ArrayList<>(),
                new ArrayList<>()
            );

            ProblemData.getInstance().add(newProblem);
            dataWriter.saveProblems(ProblemData.getInstance().getProblems());

            messageLabel.setText("Problem created successfully!");
            App.setRoot("problems");

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error creating problem. Check difficulty/type values.");
        }
    }

    private Difficulty parseDifficulty(String value) {
        if (value == null) {
            return Difficulty.EASY;
        }
        return Difficulty.valueOf(value.toUpperCase());
    }

    private ProblemType parseProblemType(String value) {
        if (value == null) {
            return ProblemType.LINKEDLIST;
        }

        String cleanedValue = value.toUpperCase().replace(" ", "");
        return ProblemType.valueOf(cleanedValue);
    }

    private double parseTimer(String value) {
        if (value == null || value.equalsIgnoreCase("None")) {
            return 0.0;
        }

        if (value.contains("5")) return 5.0;
        if (value.contains("10")) return 10.0;
        if (value.contains("15")) return 15.0;
        if (value.contains("30")) return 30.0;

        return 0.0;
    }

    private ArrayList<ArrayList<String>> buildExamples() {
        ArrayList<ArrayList<String>> examples = new ArrayList<>();

        for (String output : exampleOutputs) {
            ArrayList<String> example = new ArrayList<>();
            example.add("");
            example.add(output);
            examples.add(example);
        }

        if (examples.isEmpty()) {
            ArrayList<String> defaultExample = new ArrayList<>();
            defaultExample.add("");
            defaultExample.add("No example provided.");
            examples.add(defaultExample);
        }

        return examples;
    }

    private ArrayList<ArrayList<String>> buildAnswers() {
        ArrayList<ArrayList<String>> answers = new ArrayList<>();

        for (String output : answerOutputs) {
            ArrayList<String> answer = new ArrayList<>();
            answer.add(output);
            answers.add(answer);
        }

        return answers;
    }

    @FXML
    private void handleCancelCreation() throws IOException {
        App.setRoot("contributorDashboard");
    }
}