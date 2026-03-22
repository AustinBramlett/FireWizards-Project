package com.fwproblemsolversite.accounts;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.enums.AccountType;
/**
 * Represents an administrator account in the system.
 * Administrators have the ability to ban, mute, and terminate other accounts.
 * they can also see the reports made by the users.
 */
public class Administrator extends Account {

    private ArrayList<UUID> adminBanLog;
    private ArrayList<UUID> adminMuteLog;
    private ArrayList<String> adminTermLog;
    /**
     * Creates an Administrator account with basic user information and the admin logs.
      * 
      * @param id The unique identifier for the account.
      * @param firstName The first name of the account holder.
      * @param lastName The last name of the account holder.
      * @param username The username for login.
      * @param email The email address associated with the account.
      * @param password The password for the account.
      * @param adminBanLog The log of accounts banned by the administrator.
      * @param adminMuteLog The log of accounts muted by the administrator.
      * @param adminTermLog The log of accounts terminated by the administrator.
     */
    public Administrator(UUID id, String firstName, String lastName,
                   String username, String email, String password, ArrayList<UUID> adminBanLog, ArrayList<UUID> adminMuteLog, ArrayList<String> adminTermLog) {
        super(id, firstName, lastName, username, email, password);
        this.adminBanLog = adminBanLog;
        this.adminMuteLog = adminMuteLog;
        this.adminTermLog = adminTermLog;
    }
    /**
     * Bans an account from the system.
     * 
     * @param accountID The ID of the account to be banned.
      * @return true if the account was successfully banned, if not then false.
     */
    public boolean ban(UUID accountID) {
        return false;
    }
    /**
     * Mutes a users account.
     * 
     * @param accountID The ID of the account to be muted.
      * @return true if the account was successfully muted, if not then false.
     */
    public boolean mute(UUID accountID) {
        return false;
    }
    /**
     * Terminates a users account.
     * 
     * @param accountID The ID of the account to be terminated.
      * @return true if the account was successfully terminated, if not then false.
     */
    public boolean terminate(UUID accountID) {
        return false;
    }
    /**
     * Lets the Admin view a specific report by its ID.
     * 
     * @param reportID The ID of the report to be viewed.
     */
    public void seeReport(UUID reportID) {
    }
    /**
     * Deletes a report from the system.
     * 
     * @param reportID The ID of the report to be deleted.
     */
    public void deleteReport(UUID reportID) {
    }
    /**
     * Displays all the current reports in the system.
     */
    public void seeAllReports() {
    }
    @Override
    /**
     * Returns the Account type for the user.
     * 
     * @return The AccountType of the user.
     */
    public AccountType getAccountType() {
        return AccountType.ADMIN;
    }
}