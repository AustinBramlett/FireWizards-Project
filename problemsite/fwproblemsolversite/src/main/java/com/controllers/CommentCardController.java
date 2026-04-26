package com.controllers;

import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.problems.Comment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CommentCardController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label commentText;
    private Comment comment;
    private ProblemController parentController;

    public void setData(Comment c, ProblemController parent) {
        this.comment = c;
        this.parentController = parent;

        usernameLabel.setText(
            ProblemApplication.getInstance()
                .getAccountData()
                .getAccountById(c.getSender())
                .getUsername()
        );
        dateLabel.setText(c.getDate().toString());
        commentText.setText(c.getCommentText());
    }

    @FXML
    private void handleReply() {
        if (parentController != null && comment != null) {
            parentController.handleReply(comment);
        }

    }
}
