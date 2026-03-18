package com.fwproblemsolversite.problems;

import java.util.UUID;
import java.util.ArrayList;

public class Comment {

    private String commentText;
    private UUID sender;
    private UUID problemID;
    private int score;
    private ArrayList<Comment> replies;
    // Constructor to initialize the comment with sender and body
    public Comment(UUID sender, String body) {
        this.sender = sender;
        this.commentText = body;
        this.score = 0;
        this.replies = new ArrayList<>();
    }
    //
    public Comment(UUID sender, String body, int score) {
        this.sender = sender;
        this.commentText = body;
        this.score = score;
        this.replies = new ArrayList<>();
    }
    // Method to add a reply to the comment
    public void addReply(Comment reply) {
        this.replies.add(reply);
    }
    // Getters and Setters
    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public void editComment() {
    }

    public void deleteComment() {
    }
}