package com.fwproblemsolversite.data;

import java.util.ArrayList;
import com.fwproblemsolversite.Report;

public class ReportData {

    private ArrayList<Report> reports;
    private static ReportData reportData;

    private ReportData() {
        reports = new ArrayList<>();
    }

    public static ReportData getInstance() {
        if (reportData == null) {
            reportData = new ReportData();
        }
        return reportData;
    }

    public void printReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports to display.");
        } else {
            for (Report report : reports) {
                System.out.println(report);
            }
        }
    }

    public void removeReport(Report report) {
        if (report == null) return;
        reports.remove(report);
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void addReport(Report report) {
        if (report == null) return;
        reports.add(report);
    }
}