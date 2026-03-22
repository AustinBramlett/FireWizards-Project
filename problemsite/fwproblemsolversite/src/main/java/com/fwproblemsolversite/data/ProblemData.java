package com.fwproblemsolversite.data;

import java.util.ArrayList;

import com.fwproblemsolversite.problems.Problem;
/**
 * Manages all the problem data in the system.
 * 
 *Provides methods to store, retrieve, and to search for problems.
 */
public class ProblemData {

    private ArrayList<Problem> problems;
    private static ProblemData problemData;
    /**
     * Creates a new instance of ProblemData.
     * Initializes the list of problems.
     */
    public ProblemData() {
        problems = new ArrayList<>();
    }
    /**
     * Returns the list of problems.
     * 
     * @return The list of problems.
     */
    public ArrayList<Problem> getProblems() {
        return problems;
    }
    /**
     * Adds a problem to the system.
     * 
     * @param problem The problem to add to the system.
     */
    public void add(Problem problem) {
        problems.add(problem);
    }
    /**
     * Removes a problem from the system by its ID.
     * 
     * @param id The ID of the problem to remove.
     */
    public void remove(String id) {
    }
    /**
     * Searches for problems by a tag.
     * 
     * @param tag The tag to search for.
     * @return A list of problems that match the tag.
     */
    public ArrayList<Problem> searchByTag(String tag) {
        return null;
    }
    /**
     * Searches for problems by difficulty.
     * 
     * @return A list of problems sorted by difficulty.
     */
    public ArrayList<Problem> searchByDifficulty() {
        return null;
    }
    /**
     * Searches for problems alphabetically by title.
     * 
     * @return A list of problems sorted alphabetically by title.
     */
    public ArrayList<Problem> searchByAlphabet() {
        return null;
    }
}