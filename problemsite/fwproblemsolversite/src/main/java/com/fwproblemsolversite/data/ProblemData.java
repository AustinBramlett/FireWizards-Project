package com.fwproblemsolversite.data;

import java.util.ArrayList;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.enums.Difficulty;

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
        ArrayList<Problem> results = new ArrayList<>();

        if (tag == null || tag.trim().isEmpty()) {
            return results;
        }
        for (Problem problem : problems) {
            if (problem.getTags() != null) {
                for (String currentTag : problem.getTags()) {
                    if (currentTag.equalsIgnoreCase(tag)) {
                        results.add(problem);
                        break;
                    }
                }
            }
        }
        return results;
    }

    /**
     * Searches for problems by difficulty.
     * @param difficulty
     * @return
     */
    public ArrayList<Problem> searchByDifficulty(Difficulty difficulty) {
        ArrayList<Problem> results = new ArrayList<>();

        if (difficulty == null) {
            return results;
        }

        for (Problem problem : problems) {
            if (problem.getDifficulty() == difficulty) {
                results.add(problem);
            }
        }

        return results;
    }
    /**
     * Searches for problems alphabetically by title.
     *
     * @return A list of problems sorted alphabetically by title.
     */
    public ArrayList<Problem> searchByAlphabet() {
        return null;
    }

    /**
     * Searches for problems by title, allowing for partial matches.
     * @param query
     * @return
     */
    public ArrayList<Problem> searchByTitle(String query) {
        ArrayList<Problem> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        for (Problem problem : problems) {
            if (problem.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(problem);
            }
        }
        return results;
    }

}
