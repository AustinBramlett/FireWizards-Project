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
                   String answer) {
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
}