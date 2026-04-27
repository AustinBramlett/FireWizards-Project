package com.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Problem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML private TextArea codeArea;

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
    private final ArrayList<String> exampleTitles = new ArrayList<>();
    private final ArrayList<String> notes = new ArrayList<>();
    private final ArrayList<String> answers = new ArrayList<>();
    private ProblemApplication problemApp = ProblemApplication.getInstance();

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

        HBox itemRow = new HBox(8.0);
        itemRow.setStyle("-fx-alignment: center-left;");

        Label itemLabel = new Label(text);
        itemLabel.getStyleClass().add("tag-pill");

        Button removeButton = new Button("-");
        removeButton.getStyleClass().add("danger-button");
        removeButton.setOnAction(event -> {
            list.remove(text);
            box.getChildren().remove(itemRow);
        });

        itemRow.getChildren().addAll(itemLabel, removeButton);
        box.getChildren().add(itemRow);

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

        String temp = title + " | " + complexity + " | " + expectedOutput;

        if (selectedFile != null) {
            temp += " | File: " + selectedFile.getName();
        }

        final String answerText = temp;

        answers.add(answerText);

        HBox answerRow = new HBox(8.0);
        answerRow.setStyle("-fx-alignment: center-left;");

        Label answerLabel = new Label(title + " (" + complexity + ")");
        answerLabel.getStyleClass().add("tag-pill");

        Button removeButton = new Button("x");
        removeButton.getStyleClass().add("danger-button");
        removeButton.setOnAction(event -> {
            answers.remove(answerText);
            answersBox.getChildren().remove(answerRow);
        });

        answerRow.getChildren().addAll(answerLabel, removeButton);
        answersBox.getChildren().add(answerRow);

        answerTitleField.clear();
        timeComplexityField.clear();
        answerDescriptionArea.clear();
        selectedFile = null;

        messageLabel.setText("Answer added.");
}

    @FXML
    private void handleCreateProblem() throws IOException {

        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String summary = summaryField.getText().trim();
        String code = codeArea.getText().trim();
        ArrayList<ArrayList<String>> examples = new ArrayList<>();
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

        if (code.isEmpty()) {
            messageLabel.setText("Please enter the code users will see.");
            return;
        }
        if(exampleOutputs.size() == exampleTitles.size()){
            for (int i = 0; i < exampleOutputs.size(); i++) {
                ArrayList<String> example = new ArrayList<>();
                example.add(exampleTitles.get(i));
                example.add(exampleOutputs.get(i));
                examples.add(example);
            }
        }
        Difficulty difficulty = Difficulty.valueOf(difficultyBox.getValue().toUpperCase());
        ProblemType type = ProblemType.valueOf(typeBox.getValue().toUpperCase());
        ArrayList<ArrayList<String>> answersAdj = new ArrayList<>();
        answersAdj.add(answers);
        problemApp.addProblem(
            title,
            description,
            constraints,
            Language.JAVA,
            examples,
            notes,
            type,
            tags,
            Double.valueOf(timerBox.getValue()),
            answersAdj,
            difficulty,
            code
        );

        messageLabel.setText("Problem created successfully!");
        clearForm();

        App.setRoot("problems");
    }

    private void clearForm() {
        titleField.clear();
        descriptionArea.clear();
        summaryField.clear();
        codeArea.clear();

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