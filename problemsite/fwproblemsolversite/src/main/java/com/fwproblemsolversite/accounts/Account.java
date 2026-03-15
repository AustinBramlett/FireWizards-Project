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

    public void comment(String comment) {
        if (muted) {
            System.out.println(username + " is muted and cannot comment.");
        } else {
            // Handle comment logic here
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

    public String getPassword() {
    return password;
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
    
}