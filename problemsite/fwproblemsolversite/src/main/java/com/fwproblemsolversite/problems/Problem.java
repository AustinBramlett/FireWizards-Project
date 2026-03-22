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
    private ArrayList<ArrayList<String>> examples;
    private Language language;
    private ArrayList<String> constraints;
    //New problem constructor with all fields
    public Problem(String title,
                   String description,
                   ArrayList<String> constraints,
                   Language language,
                   ArrayList<ArrayList<String>> examples,
                   ArrayList<String> notes,
                   ProblemType type,
                   ArrayList<String> tags,
                   double timer,
                   String answer, Difficulty difficulty) {
        this.title = title;
        this.problemID = UUID.randomUUID();
        this.description = description;
        this.constraints = constraints;
        this.language = language;
        this.examples = examples;
        this.notes = notes;
        this.type = type;
        this.tags = tags;
        this.timer = new Timer(timer);
        this.answer = answer;
        this.difficulty = difficulty;
        this.comments = new ArrayList<>();
        this.submissions = new ArrayList<>();
    }
    //Saved problem constructor with all fields
    public Problem(String title, UUID problemID,
                   String description,
                   ArrayList<String> constraints,
                   Language language,
                   ArrayList<ArrayList<String>> examples,
                   ArrayList<String> notes,
                   ProblemType type,
                   ArrayList<String> tags,
                   double timer,
                   String answer, 
                   Difficulty difficulty, 
                   ArrayList<Comment> comments, 
                   ArrayList<Submission> submissions) {
        this.title = title;
        this.problemID = problemID;
        this.description = description;
        this.constraints = constraints;
        this.language = language;
        this.examples = examples;
        this.notes = notes;
        this.type = type;
        this.tags = tags;
        this.timer = new Timer(timer);
        this.answer = answer;
        this.difficulty = difficulty;
        this.comments = comments;
        this.submissions = submissions;
    }
    @Override
    public String toString() {
        return "Problem{" +
                "title='" + title + '\'' +
                ", problemID=" + problemID +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty +
                ", type=" + type +
                ", tags=" + tags +
                ", timer=" + timer +
                ", comments=" + comments +
                ", submissions=" + submissions +
                ", answer='" + answer + '\'' +
                ", notes=" + notes +
                ", examples=" + examples +
                ", language=" + language +
                ", constraints=" + constraints +
                '}';
    }
    public void displayProblem() {
        System.out.println(this); //placeholder
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public UUID getID() {
        return problemID;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getConstraints() {
        return constraints;
    }

    public Language getLanguage() {
        return language;
    }

    public ArrayList<ArrayList<String>> getExamples() {
        return examples;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public ProblemType getType() {
        return type;
    }

    public Timer getTimer() {
        return timer;
    }

    public double getTimeLimit() {
        return timer.toDouble();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String getAnswer() {
        return answer;
    }
    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }
    public ArrayList<ArrayList<String>> getSubmissionsArray() {
        ArrayList<ArrayList<String>> submissionsArray = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < submissions.size(); i++){
            submissionsArray.add(submissions.get(i).asArrayList());
        }
        return submissionsArray;
    }
}