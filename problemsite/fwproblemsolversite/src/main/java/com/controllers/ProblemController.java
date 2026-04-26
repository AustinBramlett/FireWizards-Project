package com.controllers;

import com.fwproblemsolversite.App;
import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProblemController {

    private ProblemApplication problemApp = ProblemApplication.getInstance();
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private HBox tagsBox;
    @FXML
    private VBox examplesBox;
    @FXML
    private TextArea codeArea;
    @FXML
    private TextArea inputArea;
    @FXML
    private VBox commentsBox;
    @FXML
    private TextArea commentInput;

    @FXML
    public void initialize() {
        Problem problem = problemApp.getCurrentProblem();

        if (problem != null) {
            titleLabel.setText(problem.getTitle());
            descriptionLabel.setText(problem.getDescription());

            //Difficulty 
            switch (problem.getDifficulty()) {
                case EASY:
                    difficultyLabel.setText("Easy");
                    difficultyLabel.setStyle("-fx-background-color: #2ecc71;"
                            + "-fx-text-fill: white;"
                            + "-fx-padding: 4 10;"
                            + "-fx-background-radius: 12;"
                    );
                    break;

                case MEDIUM:
                    difficultyLabel.setText("Medium");
                    difficultyLabel.setStyle("-fx-background-color: #f1c40f;"
                            + "-fx-text-fill: black;"
                            + "-fx-padding: 4 10;"
                            + "-fx-background-radius: 12;"
                    );
                    break;
                case HARD:
                    difficultyLabel.setText("Hard");
                    difficultyLabel.setStyle("-fx-background-color: #e74c3c;"
                            + "-fx-text-fill: white;"
                            + "-fx-padding: 4 10;"
                            + "-fx-background-radius: 12;"
                    );
                    break;
            }

            //Tags
            tagsBox.getChildren().clear();

            for (String tag : problem.getTags()) {
                Label tagLabel = new Label(tag);

                tagLabel.setStyle("-fx-background-color: #3498db;"
                        + "-fx-text-fill: white;"
                        + "-fx-padding: 4 10;"
                        + "-fx-background-radius: 10;");

                tagsBox.getChildren().add(tagLabel);
            }

            //Examples
            examplesBox.getChildren().clear();

            if (problem.getExamples() != null) {
                for (int i = 0; i < problem.getExamples().size(); i++) {

                    String input = problem.getExamples().get(i).get(0);
                    String output = problem.getExamples().get(i).get(1);

                    Label exampleLabel = new Label(
                            "Example " + (i + 1) + ":\n"
                            + "Input: " + input + "\n"
                            + "Output: " + output
                    );

                    exampleLabel.setStyle("-fx-background-color: #2a2a2a; "
                            + "-fx-background-radius: 8; "
                            + "-fx-padding: 12;"
                            + "-fx-text-fill: white;");

                    exampleLabel.setWrapText(true);
                    examplesBox.getChildren().add(exampleLabel);

                }
            }
            loadComments(problem);
        }
    }

    private void loadComments(Problem problem) {
        commentsBox.getChildren().clear();

        if (problem.getComments() == null) {
            return;
        }

        for (var c : problem.getComments()) {
            renderComment(c, commentsBox, 0);
        }
    }

    @FXML
    private void handleSubmitComment() {
        String text = commentInput.getText();

        if (text == null || text.trim().isEmpty()) {
            return;
        }

        Problem problem = problemApp.getCurrentProblem();

        boolean success = problemApp.addComment(text);

        if (success) {
            commentInput.clear();
            loadComments(problem);
        }
    }

    private void renderComment(Comment c, VBox parentBox, int indentLevel) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/fwproblemsolversite/commentCard.fxml")
            );

            HBox card = loader.load();

            card.setStyle("-fx-padding: 0 0 0 " + (indentLevel * 30) + ";");

            CommentCardController controller = loader.getController();
            controller.setData(c, this);

            parentBox.getChildren().add(card);

            if (c.getReplies() != null) {
                for (Comment reply : c.getReplies()) {
                    renderComment(reply, parentBox, indentLevel + 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToProblems() {
        try {
            App.setRoot("problems");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleReply(Comment parentComment) {
        String text = commentInput.getText();

        if (text == null || text.trim().isEmpty()) {
            return;
        }

        Comment reply = new Comment(
                parentComment.getSender(),
                parentComment.getProblemID(),
                text
        );

        parentComment.addReply(reply);

        ProblemApplication.getInstance().save();

        loadComments(problemApp.getCurrentProblem());
        commentInput.clear();
    }
}
