package com.fwproblemsolversite.data;

import java.util.ArrayList;
import com.fwproblemsolversite.problems.Problem;

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

    public ArrayList<Problem> searchByTag(String tag) {
        return null;
    }

    public ArrayList<Problem> searchByDifficulty() {
        return null;
    }

    public ArrayList<Problem> searchByAlphabet() {
        return null;
    }
}