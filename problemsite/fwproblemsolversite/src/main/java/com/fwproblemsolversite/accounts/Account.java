package com.fwproblemsolversite.accounts;

import java.util.UUID;

import com.fwproblemsolversite.data.Progress;
import com.fwproblemsolversite.enums.AccountType;
/**
 * Represents a user account in the system.
 * Stores the user information like name, login credentials, and account type,
 * progress, and whether the account is muted or not.
 */
public class Account {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private AccountType accountType;
    private Progress progress;
    private boolean muted;
    private String lastDate; // Added field to store the last date of activity
    /**
     * Default constructor for Account class. Initializes all fields with default values.
     * - id is set to a random UUID
     * - firstName, lastName, username, email, and password are set to empty
     */
    public Account() {
        this.id = UUID.randomUUID();
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.email = "";
        this.password = "";
        this.accountType = AccountType.STUDENT;
        this.progress = new Progress();
        this.muted = false;
    }
    /**
     * Creates an account with basic user information.
     * 
     * @param id The unique identifier for the account.
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param username The username for login.
     * @param email The email address associated with the account.
     * @param password The password for the account.
     */
    public Account(UUID id, String firstName, String lastName, String username,
                   String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = AccountType.STUDENT;
        this.progress = new Progress();
        this.muted = false;
    }
    /**
     * Creates a fully initialized account.
     * 
     * @param id The unique identifier for the account.
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param username The username for login.
     * @param email The email address associated with the account.
     * @param password The password for the account.
     * @param accountType The type of account example STUDENT, TEACHER, ADMIN.
     * @param progress The progress of the user in the system.
     * @param muted Whether the account is muted or not.
     */
    public Account(UUID id, String firstName, String lastName, String username,
                   String email, String password, AccountType accountType,
                   Progress progress, boolean muted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.progress = progress;
        this.muted = muted;
    }
    /**
     * Allows the user to leave a comment.
     * If the account is muted, the will not be able to comment.
     * 
     * @param comment The comment to be posted.
     */
    public void comment(String comment) {
        if (muted) {
            System.out.println(username + " is muted and cannot comment.");
            return;
        } else {
            System.out.println(username + " commented: " + comment);
        }
    }
    /**
     * Updates account information.
     */
    public void updateAccount() {
        System.out.println("Account updated for " + username);
    }
    /**
     * Updates the progress of the user in the system.
     * 
     * @param newProgress The new progress to be set for the user.
     */
    public void updateProgress(Progress newProgress) {
        this.progress = newProgress;
    }

    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Progress getProgress() {
        return progress;
    }

    public String getLastDate() {
        return lastDate;
    }
    /**
     * Sends a report against another user.
     * 
     * @param reason The reason for the report.
     * @param accused The username of the user being reported.
     */
    public void sendReport(String reason, String accused) {
        System.out.println("Report sent by " + username + " against " + accused + " for reason: " + reason);
    }
    
    public boolean isMuted() {
        return muted;
    }
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
    /**
     * Returns a string representation of the account.
     * 
     * @return A string containing the account details.
     */
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                ", progress=" + progress +
                ", muted=" + muted +
                '}';
    }
    
}