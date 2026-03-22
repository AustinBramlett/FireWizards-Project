package com.fwproblemsolversite.accounts;

import java.util.UUID;
/**
 * Represents a report submitted by a user.
 * The report will contain information about the accused user,
 * and the reason they were reported.
 */
public class Report {

    private UUID id;
    private String reason;
    private String accused;
    private String sender;
    /**
     * Creates a new report with a unique ID.
     * 
     * @param reason The reason for the report.
     * @param accused The username of the accused user.
     * @param sender The username of the user submitting the report.
     */
    public Report(String reason, String accused, String sender) {
        this.id = UUID.randomUUID();
        this.reason = reason;
        this.accused = accused;
        this.sender = sender;
        
    }
    /**
     * Creates a report with a specified ID.
     * 
     * @param reason The reason for the report.
     * @param accused The username of the accused user.
     * @param sender The username of the user submitting the report.
     * @param id The unique identifier for the report.
     */
    public Report(String reason, String accused, String sender, String id) {
        this.id = UUID.fromString(id);
        this.reason = reason;
        this.accused = accused;
        this.sender = sender;
        
    }

    public UUID getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public String getAccused() {
        return accused;
    }

    public String getSender() {
        return sender;
    }
    /**
     * Submits the report to the system.
     */
    public void submitReport() {
        System.out.println("Report submitted by " + sender + 
            " against " + accused +
            " for reason: " + reason);
    }
     /**
     * Returns a string representation of the report
     * 
     * @return A string containing the report details.
     */
    @Override
    public String toString() {
        return "Report{" +
                "reason='" + reason + '\'' +
                ", accused='" + accused + '\'' +
                ", sender='" + sender + '\'' +
                ", id=" + id +
                '}';
    }
    public UUID getID() {
        return id;
    }
}
