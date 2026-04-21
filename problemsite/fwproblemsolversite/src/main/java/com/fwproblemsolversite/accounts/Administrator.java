package com.fwproblemsolversite.accounts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.enums.AccountType;
/**
 * Represents an administrator account in the system.
 * Administrators have the ability to ban, mute, and terminate other accounts.
 * they can also see the reports made by the users.
 */
public class Administrator extends Account {

    private ArrayList<UUID> adminTermLog;
    //The following values are not saved to JSON and are rather assembled after startup.
    private ArrayList<ban> adminBans;
    private ArrayList<mute> adminMutes;
    /**
     * Creates an Administrator account with basic user information and the admin logs. The logs are scraped from JSON files as individual arrays and turned into records for ease of use, with the exception of terminations.
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
      * @param adminBanDates The dates of the bans made by the administrator. Corresponds to the adminBanLog by index.
      * @param adminMuteDates The dates of the mutes made by the administrator. Corresponds to the adminMuteLog by index.
     */
    public Administrator(UUID id, String firstName, String lastName,
                   String username, String email, String password, ArrayList<UUID> adminBanLog, ArrayList<UUID> adminMuteLog, ArrayList<UUID> adminTermLog, ArrayList<LocalDate> adminBanDates, ArrayList<LocalDate> adminMuteDates) {
        super(id, firstName, lastName, username, email, password);
        for(int i = 0; i < adminBanLog.size(); i++) {
            adminBans.add(new ban(adminBanLog.get(i), adminBanDates.get(i)));
        }
        for(int i = 0; i < adminMuteLog.size(); i++) {
            adminMutes.add(new mute(adminMuteLog.get(i), adminMuteDates.get(i)));
        }
    }
    
    //Internal value types to keep track of bans and mutes for the admin. They technically just store values, and therefore don't have their own file.
    //Must be used at startup to make any actions needed to unban/unmute accounts.
    //Note that these are final by default as records and therefore are immutable! As they should be.
    /**
     * Represents a ban record for an account, containing the ID of the banned account (0) and the date the ban expires (1).
     * Stored as a record for ease of use and immutability, as these values should not be changed once they are set.
     */
    public record ban(UUID accountID, LocalDate date){};
    /**
     * Represents a mute record for an account, containing the ID of the muted account (0) and the date the mute expires (1).
     * Stored as a record for ease of use and immutability, as these values should not be changed once they are set.
     */
    public record mute(UUID accountID, LocalDate date){};

    /**
     * Bans an account from the system.
     * 
     * @param accountID The ID of the account to be banned.
     * @return true if the account was successfully banned, if not then false.
     */
    public boolean banAccount(UUID accountID, LocalDate date) {
        AccountData data = AccountData.getInstance();
        if(accountID == null || date == null) return false;
        for(Account account : data.getAccounts()) {
            if(account.getId().equals(accountID) && account.isBanned()) {
                return false;
            }
        }
        adminBans.add(new ban(accountID, date));
        data.getAccountById(accountID).setBanned(true);
        return true;
    }

    /**
     * Prematurely unbans an account from the system.
     * 
     * @param accountID The ID of the account to be unbanned.
      * @return true if the account was successfully unbanned, if not then false. Cannot unban an account that is not currently banned.
     */
    public boolean unbanAccount(UUID accountID) {
        AccountData data = AccountData.getInstance();
        if(accountID == null) return false;
        for(Account account : data.getAccounts()) {
            if(account.getId().equals(accountID) && !account.isBanned()) {
                return false;
            } else if(account.getId().equals(accountID) && account.isBanned()) {
                adminBans.removeIf(ban -> ban.accountID().equals(accountID)); //Removes all bans for the target account regardless of date.
                data.getAccountById(accountID).setBanned(false); //Internally flags the account as unbanned so they are saved as such.
                return true;
            }
        }
        return false;
    }

    /**
     * Mutes a users account.
     * 
     * @param accountID The ID of the account to be muted.
      * @return true if the account was successfully muted, if not then false. Cannot mute an account that is already muted.
     */
    public boolean muteAccount(UUID accountID, LocalDate date) {
        AccountData data = AccountData.getInstance();
        if(accountID == null || date == null) return false;
        for(Account account : data.getAccounts()) {
            if(account.getId().equals(accountID) && account.isMuted()) {
                return false;
            }
        }
        adminMutes.add(new mute(accountID, date));
        data.getAccountById(accountID).setMuted(true);
        return true;
    }

    /**
     * Prematurely unmutes a user's account.
     * @param accountID The ID of the account to be unmuted,
     * @return true if the account was successfully unmuted, if not then false. Cannot unmute an account that is not currently muted.
     */
    public boolean unmuteAccount(UUID accountID) {
        AccountData data = AccountData.getInstance();
        if(accountID == null) return false;
        for(Account account : data.getAccounts()) {
            if(account.getId().equals(accountID) && !account.isMuted()) {
                return false;
            } else if(account.getId().equals(accountID) && account.isMuted()) {
                adminMutes.removeIf(mute -> mute.accountID().equals(accountID)); //Removes all mutes for the target account regardless of date.
                data.getAccountById(accountID).setMuted(false); //Internally flags the account as unmuted so they are saved as such.
                return true;
            }
        }
        return false;
    }

    /**
     * Terminates a users account. This cannot be undone.
     * 
     * @param accountID The ID of the account to be terminated.
      * @return true if the account was successfully terminated, if not then false.
     */
    public boolean terminateAccount(UUID accountID) {
        if(accountID == null) return false;
        AccountData data = AccountData.getInstance();
        for(Account account : data.getAccounts()) {
            if(account.getId().equals(accountID)) {
                adminTermLog.add(accountID);
                data.removeAccount(account);
                return true;
            }
        }
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
    //The following methods should be used by the facade upon startup to unban/unmute accounts as needed. They are not intended to be used otherwise.
    //Ideally, the facade will call the getBans and getMutes methods on all Administrator accounts, assemble the data into a list, and then take the appropriate actions to unban/unmute accounts as needed.
    //They will also be used by dataWriter to write the current bans and mutes to JSON upon shutdown to four arrays per Administrator.

    /**
     * Returns the list of bans for the administrator.
     * @return An ArrayList of ban records, which contain the ID of the account banned and the date the ban expires.
     */
    public ArrayList<ban> getBans(){
        return adminBans;
    }

    /**
     * Returns the list of mutes for the administrator.
     * @return An ArrayList of mute records, which contain the ID of the account muted and the date the mute expires.
     */
    public ArrayList<mute> getMutes(){
        return adminMutes;
    }

    public ArrayList<UUID> getTermLog(){
        return adminTermLog;
    }
}