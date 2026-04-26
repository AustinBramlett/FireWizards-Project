package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.data.ProblemData;

public class ProblemDataTest {
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    @Test
    public void testSingleton() {
        ProblemData instance1 = ProblemData.getInstance();
        ProblemData instance2 = ProblemData.getInstance();
        assertTrue(instance1 == instance2);
    }

    @Test
    public void testAddProblem() {
        ProblemData problemData = ProblemData.getInstance();
        int initialSize = problemData.getProblems().size();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        assertEquals(initialSize + 1, problemData.getProblems().size());
    }

    @Test
    public void testSearchByTag() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("THIS IS NOT AN ACTUAL TAG");
        ArrayList<String> tags2 = new ArrayList<>();
        tags2.add("THIS IS ALSO NOT AN ACTUAL TAG");
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            tags,
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Another Problem",
            "This is another test problem.",
            new ArrayList<>(),
            Language.PYTHON,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            tags2,
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByTag("THIS IS NOT AN ACTUAL TAG");
        assertEquals(1, results.size());
    }
    @Test
    public void testSearchByTagEmpty() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<Problem> results = problemData.searchByTag("");
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByTagNull() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<Problem> results = problemData.searchByTag(null);
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByDifficulty() {
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Another Problem",
            "This is another test problem.",
            new ArrayList<>(),
            Language.PYTHON,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.HARD,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByDifficulty(Difficulty.EASY);
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchByDifficultyNull() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<Problem> results = problemData.searchByDifficulty(null);
        assertEquals(0, results.size());
    }

    @Test
    public void testRemoveProblem() {
        ProblemData problemData = ProblemData.getInstance();
        Problem problem = new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");");
        problemData.add(problem);
        int initialSize = problemData.getProblems().size();
        problemData.remove(problem.getId());
        assertEquals(initialSize - 1, problemData.getProblems().size());
    }

    @Test
    public void testRemoveProblemNonExistent() {
        ProblemData problemData = ProblemData.getInstance();
        int initialSize = problemData.getProblems().size();
        problemData.remove(UUID.randomUUID());
        assertEquals(initialSize, problemData.getProblems().size());
    }

    @Test
    public void testRemoveProblemNull() {
        ProblemData problemData = ProblemData.getInstance();
        int initialSize = problemData.getProblems().size();
        problemData.remove(null);
        assertEquals(initialSize, problemData.getProblems().size());
    }

    @Test
    public void testSearchByTitle() {
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Unique Title",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Special Name",
            "This is another test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByTitle("Unique Title");
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchByTitleNull() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<Problem> results = problemData.searchByTitle(null);
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByTitleWithNullTitle() {
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            null,
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Special Name",
            "This is another test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByTitle("Special Name");
        assertEquals(1, results.size());
    }
    
    @Test
    public void testSearchByAlphabet() {
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Beta Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Alpha Problem",
            "This is another test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByAlphabet();
        assertEquals("Alpha Problem", results.get(0).getTitle());
        assertEquals("Beta Problem", results.get(1).getTitle());
    }

    @Test
    public void testSearchByAlphabetEmpty() {
        ProblemData problemData = ProblemData.getInstance();
        ArrayList<Problem> results = problemData.searchByAlphabet();
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByAlphabetWithNullTitle() {
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            null,
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        problemData.add(new Problem(
            "Beta Problem",
            "This is another test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY,
            "System.out.println(\"Hello, world!\");"));
        ArrayList<Problem> results = problemData.searchByAlphabet();
        assertEquals(1, results.size());
    }
}
