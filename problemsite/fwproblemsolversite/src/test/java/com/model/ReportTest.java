package com.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Report;

public class ReportTest {

     // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // Tests that a new Report object is initialized with the provided values
    @Test
    public void testReportConstructorCreatesValidReport() {
        Report report = new Report("Spam", "badUser", "goodUser");

        assertNotNull(report.getID());
        assertEquals("Spam", report.getReason());
        assertEquals("badUser", report.getAccused());
        assertEquals("goodUser", report.getSender());
    }

    // Tests that a Report object can be created with a specific string ID
    @Test
    public void testReportConstructorWithStringIdCreatesValidReport() {
        String id = "123e4567-e89b-12d3-a456-426614174000";
        Report report = new Report("Harassment", "badUser", "goodUser", id);

        assertEquals(id, report.getID().toString());
        assertEquals("Harassment", report.getReason());
        assertEquals("badUser", report.getAccused());
        assertEquals("goodUser", report.getSender());
    }

    // Tests that the toString method returns a string containing the report's data
    @Test
    public void testToStringContainsReportData() {
        Report report = new Report("Spam", "badUser", "goodUser");

        String output = report.toString();

        assertTrue(output.contains("Spam"));
        assertTrue(output.contains("badUser"));
        assertTrue(output.contains("goodUser"));
    }

    // Tests that getId and getID return the same value
    @Test
    public void testGetIdAndGetIDReturnSameValue() {
        Report report = new Report("Spam", "badUser", "goodUser");

        assertEquals(report.getId(), report.getID());
    }
}