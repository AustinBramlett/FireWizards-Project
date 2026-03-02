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
    }
    
   public Account(UUID id, String firstName, String lastName,
                   String username, String email) {
    }

    public Account(String firstName, String lastName, String username,
                   String email, String password, AccountType accountType) {
    }

    public void comment(String comment) {
    }

    public void updateAccount() {
    }

    public void updateProgress(Progress newProgress) {
    }

    public String getUsername() {
        return username;
    }

    public void sendReport(String reason, String accused) {
    }
}