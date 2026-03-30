package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.problems.Submission;

public class SubmissionTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // test constructor with strings
    @Test
    public void testConstructorWithStrings() {
        UUID id = UUID.randomUUID();

        Submission sub = new Submission("code", "output", id.toString());

        assertNotNull(sub);
    }

    // test constructor with ArrayList
    @Test
    public void testConstructorWithArrayList() {
        UUID id = UUID.randomUUID();

        ArrayList<String> data = new ArrayList<>();
        data.add("code");
        data.add("output");
        data.add(id.toString());

        Submission sub = new Submission(data);

        assertNotNull(sub);
    }

    // test asArrayList method
    @Test
    public void testAsArrayList() {
        UUID id = UUID.randomUUID();

        Submission sub = new Submission("code", "output", id.toString());

        ArrayList<String> result = sub.asArrayList();

        assertEquals("code", result.get(0));
        assertEquals("output", result.get(1));
        assertEquals(id.toString(), result.get(2));
    }

    // test submitAnswer always returns false
    @Test
    public void testSubmitAnswer() {
        UUID id = UUID.randomUUID();

        Submission sub = new Submission("code", "output", id.toString());

        assertFalse(sub.submitAnswer());
    }

    // test invalid UUID in constructor throws exception
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUUID() {
        new Submission("code", "output", "invalid-uuid");
    }

    // test ArrayList constructor with missing data throws exception
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayListTooSmall() {
        ArrayList<String> data = new ArrayList<>();
        data.add("code"); // missing fields

        new Submission(data);
    }
}