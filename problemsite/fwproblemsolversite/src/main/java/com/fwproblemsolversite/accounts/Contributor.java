package com.fwproblemsolversite.accounts;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.problems.Submission;
import com.fwproblemsolversite.problems.Timer;
/**
 * Represents a contributor account in the system.
 * The Constributor can create and manage problems in the system.
 */
public class Contributor extends Account {

    private ArrayList<UUID> questionsMade;
        /**
        * Creates a Contributor account with basic user information and the questions made log.
        * 
        * @param id The unique identifier for the account.
        * @param firstName The first name of the account holder.
        * @param lastName The last name of the account holder.
        * @param username The username for login.
        * @param email The email address associated with the account.
        * @param password The password for the account.
        * @param questionsMade The log of questions made by the contributor.
        */
    public Contributor(UUID id, String firstName, String lastName, String username,
                   String email, String password, ArrayList<UUID> questionsMade) {
        super(id, firstName, lastName, username, email, password);
        this.questionsMade = questionsMade;
    }
    /**
     * Adds a problem to the system.
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
     * @param answer The answer for the problem.
      * @return true if the problem was successfully added, if not then false.
      * 
     */
    public boolean addProblem(String title, String description,
                              ArrayList<String> constraints,
                              Language language,
                              ArrayList<ArrayList<String>> examples,
                              ArrayList<String> notes,
                              ProblemType type,
                              ArrayList<String> tags,
                              double timer,
                              ArrayList<ArrayList<String>> answers,
                              Difficulty difficulty,
                              String code) {
        ProblemData data = ProblemData.getInstance();
        try{
            Problem problem = new Problem(title, description, constraints, language, examples, notes, type, tags, timer, answers, difficulty, code);
            data.add(problem);
            questionsMade.add(problem.getID());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * Removes a problem from the system.
     * 
     * @param problemID The ID of the problem to be removed.
      * @return true if the problem was successfully removed, if not then false.
      *
     */
    public boolean removeProblem(UUID problemID) {
        if(problemID == null || !this.checkAuthor(problemID)) return false;
        ProblemData data = ProblemData.getInstance();
        if(data.remove(problemID)) {
            questionsMade.remove(problemID);
            return true;
        }
        return false;
    }

    /**
     * Checks if the contributor is the author of a problem.
     * @param problemID The ID of the problem
     * @return true if the contributor is the author of the problem, false otherwise
     */
    public boolean checkAuthor(UUID problemID) {
        for(UUID id : questionsMade) {
            if (id.equals(problemID)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the list of problems created by the contributor by ID.
     * @return An ArrayList of UUIDs representing the problems created by the contributor.
     */
    public ArrayList<UUID> getQuestionsMade() {
        return questionsMade;
    }
    @Override
    /**
     * Returns the Account type for the user.
     * @return The AccountType of the user.
     */
    public AccountType getAccountType() {
        return AccountType.CONTRIBUTOR;
    }
}