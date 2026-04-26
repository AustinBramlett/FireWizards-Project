package com.fwproblemsolversite.problems;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.enums.Language;
/**
 * Represents a problem in the system.
 * 
 * The problems contain information like description, difficulty, type, tags, and more.
 */
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
    private ArrayList<ArrayList<String>> answers;
    private ArrayList<String> notes;
    private String code; // Placeholder for code file
    private ArrayList<ArrayList<String>> examples;
    private Language language;
    private ArrayList<String> constraints;
    /**
     * Creates a new problem with a generated ID
     * 
     * @param title The title of the problem.
     * @param description The description of the problem.
     * @param constraints The constraints of the problem.
     * @param language The programming language of the problem.
     * @param examples The examples of the problem.
     * @param notes The notes for the problem.
     * @param type The type of the problem.
     * @param tags The tags associated with the problem.
     * @param timer The time limit for the problem.
     * @param answers The answers for the problem.
     * @param difficulty The difficulty of the problem.
     */
    public Problem(String title,
                   String description,
                   ArrayList<String> constraints,
                   Language language,
                   ArrayList<ArrayList<String>> examples,
                   ArrayList<String> notes,
                   ProblemType type,
                   ArrayList<String> tags,
                   double timer,
                   ArrayList<ArrayList<String>> answers, Difficulty difficulty, String code) {
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
        this.answers = answers;
        this.difficulty = difficulty;
        this.comments = new ArrayList<>();
        this.submissions = new ArrayList<>();
        this.code = code;
    }
    /**
     * Creates a problem from the stored data.
     * 
     * @param title The title of the problem.
     * @param problemID The unique identifier for the problem.
     * @param description The description of the problem.
     * @param constraints The constraints of the problem.
     * @param language The programming language of the problem.
     * @param examples The examples of the problem.
     * @param notes The notes for the problem.
     * @param type The type of the problem.
     * @param tags The tags associated with the problem.
     * @param timer The time limit for the problem.
     * @param answers The answers for the problem.
     * @param difficulty The difficulty of the problem.
     * @param comments The list of comments on the problem.
     * @param submissions The list of submissions for the problem.
     */
    public Problem(String title, UUID problemID,
                   String description,
                   ArrayList<String> constraints,
                   Language language,
                   ArrayList<ArrayList<String>> examples,
                   ArrayList<String> notes,
                   ProblemType type,
                   ArrayList<String> tags,
                   double timer,
                   ArrayList<ArrayList<String>> answers, 
                   Difficulty difficulty, 
                   ArrayList<Comment> comments, 
                   ArrayList<Submission> submissions,
                   String code) {
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
        this.answers = answers;
        this.difficulty = difficulty;
        this.comments = (comments == null) ? new ArrayList<>() : comments;
        this.submissions = (submissions == null) ? new ArrayList<>() : submissions;
        this.code = code;
    }
    /**
     * Returns a string representation of the problem.
     * 
     * @return string containing the problem details.
     */
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
                ", answers='" + answers + '\'' +
                ", notes=" + notes +
                ", examples=" + examples +
                ", language=" + language +
                ", constraints=" + constraints +
                '}';
    }
    /**
     * Returns the answers as an ArrayList<ArrayList<String>>
     * @return The answers.
     */
    public ArrayList<ArrayList<String>> getAnswers() {
        return answers;
    }
    /**
     * Gives a String containing all the answers to this Problem in a readable format.
     * @apiNote The first value is the title, the second value is the description, the third value is the time complexity, and the fourth value is the code filename.
     */
    public String stringAnswers() {
        String output = "";
        int index = 0;
        for(int i = 0; i < answers.size(); i++){
            index++;
            output += ("Solution " + index + " - " + answers.get(i).get(0) + ":" + System.lineSeparator()); //Title
            output += (answers.get(i).get(1) + System.lineSeparator()); //Description
            output += ("Time Complexity: O(" + answers.get(i).get(2) + ")" + System.lineSeparator()); //Time complexity
            output += ("Code: " + System.lineSeparator());
            output += (answers.get(i).get(3) + System.lineSeparator()); //Code file stand-in
            output += (System.lineSeparator());
        }
        return output;
    }
    /**
     * Displays the problem details.
     */
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

    public String getCode() {
        return code;
    }

    public double getTimeLimit() {
        return timer.toDouble();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    /**
     * Adds a comment to the problem.
     * 
     * @param comment The comment to add to a problem.
     */
    public void addComment(Comment comment) {
        if(comment != null) {
        comments.add(comment);
        }
    }
    /**
     * Reutrns the submissions to the problem.
     * @return An ArrayList of submissions.
     */
    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }
    /**
     * Converts the submissions into a Arraylist for JSON file.
     * 
     * @return list of submissions as arrays
     */
    public ArrayList<ArrayList<String>> getSubmissionsArray() {
        ArrayList<ArrayList<String>> submissionsArray = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < submissions.size(); i++){
            submissionsArray.add(submissions.get(i).asArrayList());
        }
        return submissionsArray;
    }
    /**
     * Gets the ID of the problem.
     * @return The UUID of the problem.
     */
    public UUID getId() {
        return problemID;
    }
}