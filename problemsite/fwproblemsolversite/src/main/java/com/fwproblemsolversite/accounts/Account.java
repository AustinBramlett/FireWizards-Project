package com.fwproblemsolversite.accounts;

import java.util.UUID;

import com.fwproblemsolversite.data.Progress;
import com.fwproblemsolversite.enums.AccountType;
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

    public void comment(String comment) {
        if (muted) {
            System.out.println(username + " is muted and cannot comment.");
            return;
        } else {
            System.out.println(username + " commented: " + comment);
        }
    }

    public void updateAccount() {
        System.out.println("Accountupdated for " + username);
    }

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