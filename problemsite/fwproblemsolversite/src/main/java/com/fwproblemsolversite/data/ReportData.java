package com.fwproblemsolversite.data;

import java.util.ArrayList;
import com.fwproblemsolversite.Report;

public class ReportData {

    private ArrayList<Report> reports;
    private static ReportData reportData;

    // Private constructor to prevent instantiation from outside the class
    private ReportData() {
        reports = new ArrayList<>();
    }

    // Static method to get the singleton instance of ReportData
    public static ReportData getInstance() {
        if (reportData == null) {
            reportData = new ReportData();
        }
        return reportData;
    }

    // Method to print all reports in the system
    public void printReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports to display.");
        } else {
            for (Report report : reports) {
                System.out.println(report);
            }
        }
    }
    
    // Method to remove a report from the system
    public void removeReport(Report report) {
        if (report == null) return;
        reports.remove(report);
    }

    // Getter for the list of reports
    public ArrayList<Report> getReports() {
        return reports;
    }

    // Method to add a report to the system
    public void addReport(Report report) {
        if (report == null) return;
        reports.add(report);
    }
}