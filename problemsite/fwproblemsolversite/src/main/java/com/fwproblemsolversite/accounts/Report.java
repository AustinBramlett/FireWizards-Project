package com.fwproblemsolversite.accounts;

import java.util.UUID;

public class Report {

    private UUID id;
    private String reason;
    private String accused;
    private String sender;

    public Report(String reason, String accused, String sender) {
        this.id = UUID.randomUUID();
        this.reason = reason;
        this.accused = accused;
        this.sender = sender;
        
    }
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

    public void submitReport() {
        System.out.println("Report submitted by " + sender + " against " + accused + " for reason: " + reason);
    }

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
    public String getAccused() {
        return accused;
    }
    public String getReason() {
        return reason;
    }
    public String getSender() {
        return sender;
    }
}
