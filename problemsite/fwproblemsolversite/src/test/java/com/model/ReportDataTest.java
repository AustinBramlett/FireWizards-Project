package com.model;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import com.fwproblemsolversite.data.ReportData;
import com.fwproblemsolversite.accounts.Report;

public class ReportDataTest {
    @Test
    public void testBasic() {
        assertFalse(false);
    }

    @Test
    public void testPrintReportsEmpty() {
        ReportData reportData = ReportData.getInstance();
        reportData.printReports(); // Should print "No reports to display." But we cannot test this easily just yet. It'll require a change to the method.
    }

    @Test
    public void testSingleton() {
        ReportData instance1 = ReportData.getInstance();
        ReportData instance2 = ReportData.getInstance();
        assertFalse(instance1 != instance2); // Both instances should be the same
    }

    @Test
    public void testGetReports() {
        ReportData reportData = ReportData.getInstance();
        Report report1 = new Report("Report 1", "Accused 1", "Reporter 1");
        Report report2 = new Report("Report 2", "Accused 2", "Reporter 2");
        reportData.addReport(report1);
        reportData.addReport(report2);

        assertFalse(reportData.getReports().isEmpty());
    }

    @Test
    public void testRemoveReport() {
        ReportData reportData = ReportData.getInstance();
        Report report1 = new Report("Test Report 1", "Accused1", "Reporter1");
        Report report2 = new Report("Test Report 2", "Accused2", "Reporter2");
        reportData.addReport(report1);
        reportData.addReport(report2);
        reportData.removeReport(report1);
        reportData.printReports(); // Placeholder.
    }

    @Test
    public void testRemoveReportNull() {
        ReportData reportData = ReportData.getInstance();
        Report report1 = new Report("Test Report 1", "Accused1", "Reporter1");
        reportData.addReport(report1);
        reportData.removeReport(null); // Should not throw an exception
        reportData.printReports(); // Placeholder.
    }

}
