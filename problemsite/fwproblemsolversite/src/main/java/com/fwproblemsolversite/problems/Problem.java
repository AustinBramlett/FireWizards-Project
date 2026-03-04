package com.fwproblemsolversite.problems;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.enums.Language;

public class Problem {

    private String title;
    private UUID problemID;
    private String description;
    private Difficulty difficulty;
    private ProblemType type;
    private ArrayList<String> tags;
    private Timer timer;
    private ArrayList<Comment> comments;
    private ArrayList<Submission> submissions;
    private String answer;
    private ArrayList<String> notes;
    private ArrayList<String[]> examples;
    private Language language;
    private ArrayList<String> constraints;

    public Problem(String title, UUID problemID,
                   String description,
                   ArrayList<String> constraints,
                   Language language,
                   ArrayList<String[]> examples,
                   ArrayList<String> notes,
                   ProblemType type,
                   ArrayList<String> tags,
                   double timer,
                   String answer, Difficulty difficulty) {
    }

    public void displayProblem() {
    }

    public ArrayList<String> getTags() {
        return null;
    }

    public Difficulty getDifficulty() {
        return null;
    }

    public UUID getID() {
        return null;
    }

    public Object getTitle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    public Object getDescription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
    }

    public Object getConstraints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConstraints'");
    }

    public Object getLanguage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLanguage'");
    }

    public Object getExamples() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExamples'");
    }

    public Object getNotes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNotes'");
    }

    public Object getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    public Object getTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimer'");
    }

    public Object getComments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComments'");
    }

    public Object getAnswer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnswer'");
    }
}