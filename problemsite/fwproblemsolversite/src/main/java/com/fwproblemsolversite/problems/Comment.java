package com.fwproblemsolversite.problems;

import java.util.UUID;

public class Comment {

    private String commentText;
    private UUID sender;
    private UUID problemID;
    private int score;

    public Comment(UUID sender, String body) {
        this.sender = sender;
        this.commentText = body;
        this.score = 0;
    }

    public Comment(UUID sender, String body, int score) {
        this.sender = sender;
        this.commentText = body;
        this.score = score;
    }
    public void editComment() {
    }

    public void deleteComment() {
    }
}