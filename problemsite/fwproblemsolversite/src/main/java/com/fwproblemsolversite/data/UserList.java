package com.fwproblemsolversite.data;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.accounts.Account;

public class UserList {

    private ArrayList<Account> users;
    // Constructor to initialize the user list.
    public UserList() {
        users = new ArrayList<>();
    }
    // Method to set the list of users in the system.
    public void setUsers(ArrayList<Account> users) {
        this.users = users;
    }
    // Method to retrieve a user by their unique ID.
    public Account getUserByID(UUID id) {
        for (Account user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    // Method to retrieve a user by their username and password.
    public Account getUser(String username, String password) {
        for (Account user : users) {
            if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}