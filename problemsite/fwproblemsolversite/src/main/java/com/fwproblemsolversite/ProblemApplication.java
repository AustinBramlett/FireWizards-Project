package com.fwproblemsolversite;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.UserList;
import com.fwproblemsolversite.drivers.dataLoader;
// Main application class for the problem-solving platform.
public class ProblemApplication {

    private UserList userList;
    // Constructor to initialize the application and load user data.
    public ProblemApplication() {
        userList = new UserList();
        userList.setUsers(dataLoader.LoadAccounts());
    }

    // Login Method:
    public Account login(String username, String password) {
        return userList.getUser(username, password);
    }
}