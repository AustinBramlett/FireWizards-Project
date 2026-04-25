package com.fwproblemsolversite;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.UUID;
import com.fwproblemsolversite.accounts.Administrator;
import com.fwproblemsolversite.accounts.Contributor;
import com.fwproblemsolversite.accounts.Student;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.data.ReportData;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.io.dataLoader;
import com.fwproblemsolversite.io.dataWriter;
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

    public void save() {
        dataWriter.saveAccounts(AccountData.getInstance().getAccounts());
        dataWriter.saveProblems(ProblemData.getInstance().getProblems());
        dataWriter.saveReports(ReportData.getInstance().getReports());
    }
    /**
     * Creates a new account with the given details.
     * 
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param type The type of account (Student, Contributor, Admin).
     * @return true if the account is created successfully, false otherwise.
     */
    public boolean createAccount(String firstName, String lastName, String username,
                                 String email, String password, AccountType type) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (accountData.usernameExists(username)) {
            return false;
        }

        Account newAccount;

        switch (type){
            case STUDENT:
                newAccount = new Student(
                    java.util.UUID.randomUUID(),
                    firstName,
                    lastName,
                    username,
                    email,
                    password
                );
                break;
                
            case CONTRIBUTOR:
                newAccount = new Contributor(
                    java.util.UUID.randomUUID(),
                    firstName,
                    lastName,
                    username,
                    email,
                    password,
                    new ArrayList<UUID>()
                );
                break;

            case ADMIN:
                newAccount = new Administrator(
                    java.util.UUID.randomUUID(),
                    firstName,
                    lastName,
                    username,
                    email,
                    password,
                    new ArrayList<UUID>(),
                    new ArrayList<UUID>(),
                    new ArrayList<UUID>(),
                    new ArrayList<LocalDate>(),
                    new ArrayList<LocalDate>()
                );    
                break;

                default:
                    return false;
        }

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
     * Only users with Contributor or Admin roles can add problems.
     * 
     * @return true if the problem is added successfully, if not false
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
        ArrayList<ArrayList<String>> answers,
        Difficulty difficulty
    ) {
        if (currentUser == null) return false;

        if (currentUser.getAccountType() != com.fwproblemsolversite.enums.AccountType.CONTRIBUTOR &&
            currentUser.getAccountType() != com.fwproblemsolversite.enums.AccountType.ADMIN) {
            return false;
        }

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
        answers,
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

    /**
     * Generates default problems for the system if there are no problems currently.
     * 
     */
    public void generateDefaultProblems() {
    if (!problemData.getProblems().isEmpty()) {
        return;
    }

    Account previousUser = currentUser;

    Account tempContributor = new Contributor(
        java.util.UUID.randomUUID(),
        "System",
        "Contributor",
        "system_contributor",
        "system@fw.com",
        "temp123",
        new ArrayList<UUID>()
    );

    currentUser = tempContributor;

    ArrayList<String> constraints1 = new ArrayList<>();
    constraints1.add("Array can contain negative numbers");

    ArrayList<ArrayList<String>> examples1 = new ArrayList<>();
    ArrayList<String> example1a = new ArrayList<>();
    example1a.add("[1, -1, 5, -2, 3], k = 3");
    example1a.add("4");

    ArrayList<String> example1b = new ArrayList<>();
    example1b.add("[-2, -1, 2, 1], k = 1");
    example1b.add("2");

    examples1.add(example1a);
    examples1.add(example1b);

    ArrayList<String> answer1a = new ArrayList<>();
    answer1a.add("Example Answer");
    answer1a.add("This is an example description!");
    answer1a.add("n");
    answer1a.add("ExampleCode.java");
    ArrayList<ArrayList<String>> answers1 = new ArrayList<>();
    answers1.add(answer1a);
    ArrayList<String> notes1 = new ArrayList<>();
    notes1.add("Brute force is O(n^2)");
    notes1.add("HashMap solution is O(n)");

    ArrayList<String> tags1 = new ArrayList<>();
    tags1.add("Array");
    tags1.add("HashMap");

    addProblem(
        "Longest Subarray with Sum K",
        "Find the length of the longest contiguous subarray whose sum equals k.",
        constraints1,
        com.fwproblemsolversite.enums.Language.JAVA,
        examples1,
        notes1,
        com.fwproblemsolversite.enums.ProblemType.ARRAY,
        tags1,
        30.0,
        answers1,
        Difficulty.MEDIUM
    );

    ArrayList<String> constraints2 = new ArrayList<>();
    constraints2.add("Each input has exactly one solution");

    ArrayList<ArrayList<String>> examples2 = new ArrayList<>();
    ArrayList<String> example2a = new ArrayList<>();
    example2a.add("[2, 7, 11, 15], target = 9");
    example2a.add("[0, 1]");
    examples2.add(example2a);
    ArrayList<ArrayList<String>> answers2 = new ArrayList<>();
    ArrayList<String> answer2a = new ArrayList<>();
    answer2a.add("Example Answer");
    answer2a.add("This is an example description!");
    answer2a.add("n");
    answer2a.add("ExampleCode.java");
    answers2.add(answer2a);
    ArrayList<String> notes2 = new ArrayList<>();
    notes2.add("A HashMap can store visited values");

    ArrayList<String> tags2 = new ArrayList<>();
    tags2.add("Array");
    tags2.add("HashMap");

    addProblem(
        "Two Sum",
        "Return indices of the two numbers such that they add up to target.",
        constraints2,
        com.fwproblemsolversite.enums.Language.JAVA,
        examples2,
        notes2,
        com.fwproblemsolversite.enums.ProblemType.ARRAY,
        tags2,
        20.0,
        answers2,
        Difficulty.EASY
    );

    currentUser = previousUser;
}
}