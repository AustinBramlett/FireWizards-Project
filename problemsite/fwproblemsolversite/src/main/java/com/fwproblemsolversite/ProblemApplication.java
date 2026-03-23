package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.drivers.dataLoader;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.enums.Difficulty;

// Main application class for the problem-solving platform.
public class ProblemApplication {

    private AccountData accountData;
    private ProblemData problemData;
    private Account currentUser;

    /**
     * Constructor to initialize the application and load data
     */
    public ProblemApplication() {
        accountData = AccountData.getInstance();
        accountData.setAccounts(dataLoader.LoadAccounts());

        problemData = new ProblemData();
        problemData.getProblems().addAll(dataLoader.LoadProblems());

        currentUser = null;
    }

    /**
     * Logs in a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The logged-in user, or null if login fails.
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
     * Logs out the current user.
     */
    public void logout() {
        currentUser = null;
        System.out.println("Users logged out successfully.");
    }

    /**
     * Creates a new account with the given details.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return true if the account is created successfully, false otherwise.
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
     * Solves a problem by its title.
     * @param problemTitle The title of the problem to solve.
     * @return true if the problem is solved successfully, false otherwise.
     */
    public boolean solveProblem(String problemTitle) {
        if (currentUser == null) return false;

        for (Problem problem : problemData.getProblems()) {
            if (problem.getTitle().equalsIgnoreCase(problemTitle)) {

                currentUser.getProgress()
                    .updateProgress("1", problem.getDifficulty());
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a comment to a problem by its title.
     * @param problemTitle The title of the problem to comment on.
     * @param text The text of the comment.
     * @return true if the comment is added successfully, false otherwise.
     */
    public boolean addComment(String problemTitle, String text) {
        if (currentUser == null) return false;
        if (text == null || text.trim().isEmpty()) return false;

        for (Problem problem : problemData.getProblems()) {
            if (problem.getTitle().equalsIgnoreCase(problemTitle)) {
                
                Comment comment = new Comment(currentUser.getId(), problem.getId(), text); 

                problem.addComment(comment);
                return true;
            }
        }
        return false;
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

    public ArrayList<Problem> searchByTitle(String query) {
        return problemData.searchByTitle(query);
    }

    public ArrayList<Problem> searchByTag(String tag) {
        return problemData.searchByTag(tag);
    }

    public ArrayList<Problem> searchByDifficulty(Difficulty difficulty) {
        return problemData.searchByDifficulty(difficulty);
    }
}