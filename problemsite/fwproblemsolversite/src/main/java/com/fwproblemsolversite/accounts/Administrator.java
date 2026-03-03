package com.fwproblemsolversite.accounts;

import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends Account {

    private ArrayList<UUID> adminBanLog;
    private ArrayList<UUID> adminMuteLog;
    private ArrayList<String> adminTermLog;
    public Administrator(UUID id, String firstName, String lastName,
                   String username, String email, String password, ArrayList<UUID> adminBanLog, ArrayList<UUID> adminMuteLog, ArrayList<String> adminTermLog) {
        super(id, firstName, lastName, username, email, password);
        this.adminBanLog = adminBanLog;
        this.adminMuteLog = adminMuteLog;
        this.adminTermLog = adminTermLog;
    }

    public boolean ban(UUID accountID) {
        return false;
    }

    public boolean mute(UUID accountID) {
        return false;
    }

    public boolean terminate(UUID accountID) {
        return false;
    }

    public void seeReport(UUID reportID) {
    }

    public void deleteReport(UUID reportID) {
    }

    public void seeAllReports() {
    }
}