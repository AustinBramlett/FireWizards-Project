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

public class ProblemTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // test creating a problem
    @Test
    public void testCreateProblem() {
        Problem problem = new Problem(
            "Two Sum",
            "Find two numbers",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        );

        assertEquals("Two Sum", problem.getTitle());
        assertEquals(Difficulty.EASY, problem.getDifficulty());
    }

    // make sure problem ID is generated and not null
    @Test
    public void testProblemIDNotNull() {
        Problem problem = new Problem(
            "Test",
            "Desc",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.STRING,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.MEDIUM
        );

        assertNotNull(problem.getId());
    }

    // test adding a comment
    @Test
    public void testAddComment() {
        Problem problem = new Problem(
            "Test",
            "Desc",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.TREE,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.MEDIUM
        );

        Comment comment = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Hello");

        problem.addComment(comment);

        assertEquals(1, problem.getComments().size());
    }

    //  make sure null comments are not added
    @Test
    public void testAddNullComment() {
        Problem problem = new Problem(
            "Test",
            "Desc",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.STACK,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.MEDIUM
        );

        problem.addComment(null);

        assertEquals(0, problem.getComments().size());
    }

    // test for string answers to make sure the format is correct
    @Test
    public void testStringAnswers() {
        ArrayList<ArrayList<String>> answers = new ArrayList<>();

        ArrayList<String> answer = new ArrayList<>();
        answer.add("Title");
        answer.add("Description");
        answer.add("n");
        answer.add("code.java");

        answers.add(answer);

        Problem problem = new Problem(
            "Test",
            "Desc",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.QUEUE,
            new ArrayList<>(),
            5.0,
            answers,
            Difficulty.MEDIUM
        );

        String result = problem.stringAnswers();

        assertTrue(result.contains("Solution 1"));
        assertTrue(result.contains("Title"));
    }

    // test the time limit
    @Test
    public void testTimeLimit() {
        Problem problem = new Problem(
            "Test",
            "Desc",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.HASHMAP,
            new ArrayList<>(),
            10.0,
            new ArrayList<>(),
            Difficulty.MEDIUM
        );

        assertEquals(10.0, problem.getTimeLimit(), 0.001);
    }
}