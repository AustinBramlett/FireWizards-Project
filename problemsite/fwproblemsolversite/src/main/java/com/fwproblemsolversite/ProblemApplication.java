package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.drivers.dataLoader;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.problems.Problem;

// Main application class for the problem-solving platform.
public class ProblemApplication {

    private AccountData accountData;
    private ProblemData problemData;
    private Account currentUser;

    // Constructor to initialize the application and load data
    public ProblemApplication() {
        accountData = AccountData.getInstance();
        accountData.setAccounts(dataLoader.LoadAccounts());

        problemData = new ProblemData();
        problemData.getProblems().addAll(dataLoader.LoadProblems());

        currentUser = null;
    }

    // Login Method
    public Account login(String username, String password) {
        Account account = accountData.getAccountByUsername(username);

        if (account != null && account.getPassword().equals(password)) {
            currentUser = account;
            return currentUser;
        }

        return null;
    }

    // Logout Method
    public void logout() {
        currentUser = null;
        System.out.println("User has logged out successfully.");
    }

    // Create Account Method
    public boolean createAccount(String firstName, String lastName, String username,
                                 String email, String password) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (accountData.usernameExists(username)) {
            return false;
        }

        Account newAccount = new Account(
            java.util.UUID.randomUUID(),
            firstName,
            lastName,
            username,
            email,
            password
        );

        accountData.addAccount(newAccount);
        return true;
    }

    // Get Questions Method
    public ArrayList<Problem> getAllQuestions() {
        return problemData.getProblems();
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public Account getCurrentUser() {
        return currentUser;
    }
}