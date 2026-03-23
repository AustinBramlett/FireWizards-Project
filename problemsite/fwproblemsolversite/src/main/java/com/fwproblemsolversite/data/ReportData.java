package com.fwproblemsolversite.data;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Report;

/**
 * Manages all the reports in the system.
 * 
 * It provides methods to access, add, and remove reports from the system.
 */
public class ReportData {

    private ArrayList<Report> reports;
    private static ReportData reportData;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the list of reports.
     */
    private ReportData() {
        reports = new ArrayList<>();
    }

    /**
     * Returns the single instance of ReportData.
     * 
     * @return The ReportData instance.
      */
    public static ReportData getInstance() {
        if (reportData == null) {
            reportData = new ReportData();
        }
        return reportData;
    }

    /**
     * Prints all the reports in the system.
     * If there isnt any reports, it will print a message.
     */
    public void printReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports to display.");
        } else {
            for (Report report : reports) {
                System.out.println(report);
            }
        }
    }
    
    /**
     * Removes a report from the system.
     * 
     * @param report The report to remove
     */
    public void removeReport(Report report) {
        if (report == null) return;
        reports.remove(report);
    }

   /**
    * Returns the list of all reports.
    * 
    * @return The list of reports.
    */
    public ArrayList<Report> getReports() {
        return reports;
    }

    /**
     * Adds a report to the system.
     * 
     * @param report The report to add to the system.
     */
    public void addReport(Report report) {
        if (report == null) return;
        reports.add(report);
    }
}