package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
/**
 * Driver class used to test the functionality of the application.
 * 
 */
public class Driver {
    /**
     * Main method used to run the tests on the application.
     */
    public static void main(String[] args) {

        ProblemApplication app = new ProblemApplication();
        // Test the login function with the username and password
        Account user = app.login("jmiller", "pass123");

        if (user != null) {
            System.out.println("Login Complete.");
            System.out.println("Welcome to the system, " + user.getUsername());
        } else {
            System.out.println("Login failed.");
        }
        //SALLY SCENARIO
        System.out.println("\n--- Sally's Scenario ---");

        boolean duplicate = app.createAccount(
            "Sally", "Sparrow","ssparrow","sally@email.com", "pass123"
        );

        if(!duplicate){
            System.out.println("Duplicate account rejected");
        }

        //Create new account (different username)
        boolean created = app.createAccount(
            "Sally", "Sparrow", "ssparrow2", "sally@email.com", "pass123"
        );

        if (created){
            System.out.println("New account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }
        // Login 
        Account sally = app.login("ssparrow2", "pass123");
        System.out.println("Welcome " + sally.getUsername() + " to the system!");

        //constraints
        ArrayList<String> constraints = new ArrayList<>();
        constraints.add("Array can contain negative numbers");

        //examples
        ArrayList<ArrayList<String>> examples = new ArrayList<>();

        ArrayList<String> example1 = new ArrayList<>();
        example1.add("[1, -1, 5, -2, 3], k = 3");
        example1.add("4");

        ArrayList<String> example2 = new ArrayList<>();
        example2.add("[-2, -1, 2, 1], k = 3");
        example2.add("2");

        examples.add(example1);
        examples.add(example2);

        //notes 
        ArrayList<String> notes = new ArrayList<>();
        notes.add("Brute Force )(n^2)");
        notes.add("HAshMap 0(n)");

        //tags
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Array");
        tags.add("HashMap");
        
        //add problem
        app.addProblem(
            "Longest Subarray with Sum K",
            "Find the length of the longest contiguous subarray whose sum equals k.",
            constraints,
            com.fwproblemsolversite.enums.Language.JAVA,
            examples,
            notes,
            com.fwproblemsolversite.enums.ProblemType.ARRAY,
            tags,
            30.0,
            "bruteforce.java, hashmap.java",
            com.fwproblemsolversite.enums.Difficulty.MEDIUM
        );

        //show the problems 
        for (Problem p : app.getAllQuestions()){
            System.out.println(p.getTitle());
        }
        //logout 
        app.logout();
        System.out.println("User logged out successfully.");

        //Test the getAllQuestions function to ensure that the questions are being loaded correctly.
        ArrayList<Problem> questions = app.getAllQuestions();

        if (questions != null && questions.size() > 0) {

            System.out.println("Questions successfully loaded.");
            System.out.println("Total questions loaded: " + questions.size());

        } else {
            System.out.println("No questions were loaded.");
        }

        //Test the createAccount function to ensure that new accounts can be created successfully.
        if (app.createAccount("John", "Smith", "jsmith", "jsmith@email.com", "pass123")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }

        //Test the createAccount function with an empty username to ensure that account creation fails.
        if (app.createAccount("Jane", "Smith", "", "jane@email.com", "pass123")) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }

        //Logout the user to ensure that the logout function works correctly.
        app.logout();

        com.fwproblemsolversite.drivers.dataWriter writer =
        new com.fwproblemsolversite.drivers.dataWriter();

        writer.saveAccounts(app.getAccountData().getAccounts());
        writer.saveProblems(app.getAllQuestions());
        
    }
    
}
