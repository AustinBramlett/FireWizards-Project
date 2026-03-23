package com.fwproblemsolversite.data;

import java.util.ArrayList;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.enums.Difficulty;
public class ProblemData {

    private ArrayList<Problem> problems;
    private static ProblemData problemData;

    public ProblemData() {
        problems = new ArrayList<>();
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void add(Problem problem) {
        problems.add(problem);
    }

    public void remove(String id) {
    }

    /**
     * Searches for problems by tag.
     * @param tag
     * @return
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