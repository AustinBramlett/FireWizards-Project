package com.fwproblemsolversite.accounts;

import java.util.ArrayList;
import java.util.UUID;

public class Administrator extends Account {

    private ArrayList<UUID> adminBanLog;
    private ArrayList<UUID> adminMuteLog;
    private ArrayList<String> adminTermLog;

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