package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.drivers.dataLoader;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.problems.Comment;
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

        problemData = ProblemData.getInstance();
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

   
    /**
     * Get Questions Method
     * Retrieves all questions in the system.
     *
     * @return list of problems
     */
    
    public ArrayList<Problem> getAllQuestions() {
        return problemData.getProblems();
    }
    /**
     * Adds a new problem to the system with the given details.
     */
    public boolean addProblem(
        String title,
        String description,
        ArrayList<String> constraints,
        com.fwproblemsolversite.enums.Language language,
        ArrayList<ArrayList<String>> examples,
        ArrayList<String> notes,
        com.fwproblemsolversite.enums.ProblemType type,
        ArrayList<String> tags,
        double timer,
        String answer,
        Difficulty difficulty
    ) {

    if (title == null || title.trim().isEmpty()) return false;

    for (Problem p : problemData.getProblems()) {
        if (p.getTitle().equalsIgnoreCase(title)) {
            return false;
        }
    }
    
    Problem newProblem = new Problem(
        title,
        description,
        constraints,
        language,
        examples,
        notes,
        type,
        tags,
        timer,
        answer,
        difficulty
    );

    problemData.add(newProblem);
    return true;
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