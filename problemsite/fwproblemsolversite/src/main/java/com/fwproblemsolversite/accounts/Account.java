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
    
    public Account(UUID id, String firstName, String lastName, String username,
                   String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public UUID getId() {
    return id;
    }

    public String getPassword() {
    return password;
    }
    
    public void sendReport(String reason, String accused) {
    }

    public AccountType getAccountType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountType'");
    }

    public Object getID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getID'");
    }

    public Object getFirstName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirstName'");
    }

    public Object getLastName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastName'");
    }

    public Object getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public Object getMuted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMuted'");
    }
    
    
}