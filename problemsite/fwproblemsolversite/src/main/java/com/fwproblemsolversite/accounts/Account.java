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
    private Progress progress;
    private boolean muted;

    public Account() {
    }
    
   public Account(UUID id, String firstName, String lastName,
                   String username, String email) {
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
    /**
     * This is meant to be overidden.
     * @return AccountType of instance
    */
    public AccountType getAccountType(){
        return null;
    }

    public void updateAccount() {
    }

    public void updateProgress(Progress newProgress) {
    }

    public String getUsername() {
        return username;
    }
    //This is here for data!!! We need to find a way to make this secure.. or not, I don't know.
    public String getPassword() {
        return password;
    }
    public boolean getMuted() {
        return muted;
    }
    public String getEmail() {
        return email;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getID(){
        return id.toString();
    }
    public void sendReport(String reason, String accused) {
    }
}