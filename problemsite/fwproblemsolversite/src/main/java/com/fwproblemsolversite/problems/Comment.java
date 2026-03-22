package com.fwproblemsolversite.problems;

import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDate;

public class Comment {

    private String commentText;
    private UUID sender;
    private UUID problemID;
    private int score;
    private LocalDate date;
    private ArrayList<Comment> replies;

    // Constructor to initialize the comment with sender and body
    public Comment(UUID sender,UUID problemID, String body) {
        this.sender = sender;
        this.problemID = problemID;
        this.commentText = body;
        this.date = LocalDate.now();
        this.score = 0;
        this.replies = new ArrayList<>();
    }

    // Constructor to initialize the comment with sender, body, and score
    public Comment(UUID sender, UUID problemID, String body, int score) {
        this.sender = sender;
        this.problemID = problemID;
        this.commentText = body;
        this.score = score;
        this.date = LocalDate.now();
        this.replies = new ArrayList<>();
    }

    // Method to add a reply to the comment
    public void addReply(Comment reply) {
        if (reply != null) {
            this.replies.add(reply);
        }
    }

    // Getters and Setters
    public ArrayList<Comment> getReplies() {
        return replies;
    }

    // Method to edit the comment text
    public void editComment(String newText) {
        if (newText != null && !newText.trim().isEmpty()) {
            this.commentText = newText;
        }
    }

    // Method to delete the comment by clearing its text and replies
    public void deleteComment() {
        this.commentText = "[deleted]";
        this.replies.clear();
    }

    //Getters for the comment properties
    public String getCommentText() {
        return commentText;
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getProblemID() {
        return problemID;
    }

    public int getScore() {
        return score;
    }

    public LocalDate getDate() {
        return date;
    }
}