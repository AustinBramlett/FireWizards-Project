package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.drivers.dataLoader;
import com.fwproblemsolversite.problems.Problem;

/**
 * Main application class for the system.
 * 
 * Handles the core functionality of the application.
 */
public class ProblemApplication {

    private AccountData accountData;
    private ProblemData problemData;
    private Account currentUser;

    /**
     * Initializes the application and loads account and problem data 
     * from the dataLoader class.
     */
    public ProblemApplication() {
        accountData = AccountData.getInstance();
        accountData.setAccounts(dataLoader.LoadAccounts());

        problemData = new ProblemData();
        problemData.getProblems().addAll(dataLoader.LoadProblems());

        currentUser = null;
    }

    /**
     * Logs a user into the system.
     * 
     * @param username the username of the account to log in
     * @param password the password of the account to log in   
     * @return the logged-in account if successful, null if not.
     */
    public Account login(String username, String password) {
        Account account = accountData.getAccountByUsername(username);

        if (account != null && account.getPassword().equals(password)) {
            currentUser = account;
            return currentUser;
        }

        return null;
    }

    /**
     * Logs the current user out of the system.
     */
    public void logout() {
        currentUser = null;
        System.out.println("User has logged out successfully.");
    }

    /**
     * Creates a new account in the system.
     * 
     * @param firstName the first name of the new account
     * @param lastName the last name of the new account
     * @param username the username of the new account
     * @param email the email of the new account
     * @param password the password of the new account
     * @return true if the account was created successfully, false if it was not.
     */
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
            // AccountType will be set in the specific account classes (Student, Contributor, Administrator.) 
            // In typical usage, do not use the Account constructor under any circumstances.
        );

        accountData.addAccount(newAccount);
        return true;
    }

    /**
     * Retrieves all questions in the system.
     * 
     * @return list of problems
     */
    public ArrayList<Problem> getAllQuestions() {
        return problemData.getProblems();
    }
    /**
     * Returns the account data manager.
     * 
     * @return the account data manager
     */
    public AccountData getAccountData() {
        return accountData;
    }
    /**
     * Returns the currently logged-in user.
     * 
     * @return current Account, or null if no one is logged in
     */
    public Account getCurrentUser() {
        return currentUser;
    }
}