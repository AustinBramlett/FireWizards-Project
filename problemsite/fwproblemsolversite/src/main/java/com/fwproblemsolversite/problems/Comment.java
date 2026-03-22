package com.fwproblemsolversite.problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
/**
 * Represents a comment on a problem in the system.
 * 
 * Each commnet can have replies, allowing for comment threads.
 */
public class Comment {

    private String commentText;
    private UUID sender;
    private UUID problemID;
    private int score;
    private LocalDate date;
    private ArrayList<Comment> replies;

    /**
     * Creates a new comment with defuault values.
     * 
     * @param sender The UUID of the user who made the comment.
     * @param problemID The UUID of the problem the comment is linked with.
     * @param body The text of the comment.
     */
    public Comment(UUID sender,UUID problemID, String body) {
        this.sender = sender;
        this.problemID = problemID;
        this.commentText = body;
        this.date = LocalDate.now();
        this.score = 0;
        this.replies = new ArrayList<>();
    }

   /**
    * creates a comment with all fields specified.
    * 
    * @param sender The UUID of the user who made the comment.
     * @param problemID The UUID of the problem the comment is linked with.
     * @param body The text of the comment.
     * @param score The score of the comment.
     * @param replies The list of replies to the comment.
     * @param date The date the comment was made as a string.
    */
    public Comment(UUID sender, UUID problemID, String body, int score, ArrayList<Comment> replies, String date) {
        this.sender = sender;
        this.problemID = problemID;
        this.commentText = body;
        this.score = score;
        this.date = LocalDate.parse(date);
        this.replies = replies;
    }

    /**
     * adds a reply to a comment.
     * 
     * This will support recursive threads where someone can comment on a comment.
     */
    public void addReply(Comment reply) {
        if (reply != null) {
            this.replies.add(reply);
        }
    }

    /**
     * Returns the list of replies to the comment.
     * 
     * @return The list of replies to the comment.
     */
    public ArrayList<Comment> getReplies() {
        return replies;
    }

    /**
     * Updates the text of the comment.
     * 
     * @param newText The new text for the comment.
     */
    public void editComment(String newText) {
        if (newText != null && !newText.trim().isEmpty()) {
            this.commentText = newText;
        }
    }

    /**
     * Deletes the comment and all of its replies.
     */
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

    /**
     * Returns a string representation of the comment.
     * 
     * @return A string representation of the comment.
     */
    @Override
    public String toString() {
        return "Comment{" +
                "commentText='" + commentText + "\'" +
                ", sender=" + sender +
                ", problemID=" + problemID +
                ", score=" + score +
                ", date=" + date +
                ", replies=" + replies +
                '}';
    }
}