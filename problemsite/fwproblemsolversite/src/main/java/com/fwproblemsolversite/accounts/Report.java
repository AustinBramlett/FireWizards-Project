package com.fwproblemsolversite.accounts;
import java.util.UUID;
public class Report {
    private String reason;
    private String accused;
    private String sender;
    private UUID id;
    public Report(String reason, String accused, String sender) {
        this.reason = reason;
        this.accused = accused;
        this.sender = sender;
        this.id = UUID.randomUUID();
    }
    public Report(String reason, String accused, String sender, String id) {
        this.reason = reason;
        this.accused = accused;
        this.sender = sender;
        this.id = UUID.fromString(id);
    }
    public void submitReport() {
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
