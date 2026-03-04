package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.UserList;
import com.fwproblemsolversite.drivers.dataLoader;
// Main application class for the problem-solving platform.
public class ProblemApplication {

    private UserList userList;

    private ProblemData problemData;
    // Constructor to initialize the application and load user data.
    public ProblemApplication() {
        userList = new UserList();
        userList.setUsers(dataLoader.LoadAccounts());
        // Load problem data
        problemData = new ProblemData();
        problemData.getProblems().addAll(dataLoader.LoadProblems());    
    }

    // Login Method:
    public Account login(String username, String password) {
        return userList.getUser(username, password);
    }
    // Get Questions Method:
    public ArrayList<Problem> getAllQuestions() {
        return problemData.getProblems();
    }
}