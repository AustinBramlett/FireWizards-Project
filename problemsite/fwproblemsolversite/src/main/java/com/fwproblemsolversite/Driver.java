package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
/**
 * Driver class used to test the functionality of the application.
 * 
 * Scenarios included:
 * - Account creation
 * - Contributor creation duplicate and success cases
 * - Jimmy completes daily challenge
 * 
 */
public class Driver {
    /**
     * Main method used to run the tests on the application.
     */
    public static void main(String[] args) {

        ProblemApplication app = ProblemApplication.getInstance();

        app.generateDefaultProblems();
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
            "Sally", "Sparrow","ssparrow","sally@email.com", "pass123",
            com.fwproblemsolversite.enums.AccountType.CONTRIBUTOR
        );

        if(!duplicate){
            System.out.println("Duplicate account rejected");
        }

        //Create new account (different username)
        boolean created = app.createAccount(
            "Sally", "Sparrow", "ssparrow2", "sally@email.com", "pass123",
            com.fwproblemsolversite.enums.AccountType.CONTRIBUTOR
        );

        if (created){
            System.out.println("New account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }
        // Login 
        Account sally = app.login("ssparrow2", "pass123");
        System.out.println("Welcome " + sally.getUsername() + " to the system!");

        System.out.println("Account Type: " + sally.getClass().getSimpleName());
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
        
        //answers
        ArrayList<ArrayList<String>> answers = new ArrayList<>();
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("Brute Force Approach");
        answer1.add("Try every possible subarray and compute its sum.");
        answer1.add("n^2");
        answer1.add("bruteforce.java");
        answers.add(answer1);
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("HashMap Version");
        answer2.add("Idea is to keep track of each sum in a HashMap.\nIf: currentPrefixSum = k\nThen: previous PrefixSum = currentPrefixSum - k\nSo while iterating:\nKeep track of prefix sum.\nStore the first occurrence of each prefix sum in a HashMap.\nIf (prefixSum - k) exists in the map -> we found a valid subarray.\nWhy store the first occurrence?\nBecause we want the longest subarray.");
        answer2.add("n");
        answer2.add("hashmap.java");
        answers.add(answer2);
        //notes 
        ArrayList<String> notes = new ArrayList<>();
        notes.add("What is the time complexity of your algorithm?");
        notes.add("Can you find a way to make your algorithm faster?");

        //tags
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Array");
        tags.add("HashMap");
        
        //add problem
       boolean added = app.addProblem(
            "Longest Subarray with Sum K",
            "Find the length of the longest contiguous subarray whose sum equals k.",
            constraints,
            com.fwproblemsolversite.enums.Language.JAVA,
            examples,
            notes,
            com.fwproblemsolversite.enums.ProblemType.ARRAY,
            tags,
            30.0,
            answers,
            com.fwproblemsolversite.enums.Difficulty.MEDIUM
        );

        if (added) {
            System.out.println("Problem added successfully.");
        } else {
            System.out.println("Failed to add problem.");
        }

        //show the problems 
        for (Problem p : app.getAllQuestions()){
            System.out.println(p.getTitle());
        }
        //logout 
        app.logout();
        System.out.println("User logged out successfully.");

        //JIMMY SCENARIO
        System.out.println("\n--- Jimmy Scenario ---");
        // jimmy login
        Account jimmy = app.login("jbauer", "pass123");

        if (jimmy != null) {
            System.out.println("Welcome " + jimmy.getUsername());
            //shows jimmys streak
            int streak = jimmy.getProgress().getDailyStreak();

            System.out.println("Current Streak: " + streak);

        //Get a problem for daily challenge
        Problem daily = null;
        
        for (Problem p : app.getAllQuestions()){

            if(streak >= 5 && p.getDifficulty() == com.fwproblemsolversite.enums.Difficulty.MEDIUM){
                daily = p;
                break;
            }
            if (streak < 5 && p.getDifficulty() == com.fwproblemsolversite.enums.Difficulty.EASY){
                daily = p;
                break;
            }
        }
        if (daily == null && app.getAllQuestions().size() > 0){
            daily = app.getAllQuestions().get(0);
        }
    
        System.out.println("\n--- Daily Challenge ---");
        System.out.println("Title: " + daily.getTitle());
        System.out.println("Description: " + daily.getDescription());
        System.out.println("Difficulty: " + daily.getDifficulty());
        System.out.println("Tags: " + daily.getTags());
        System.out.println("Constraints: " + daily.getConstraints());
        System.out.println("Examples: " + daily.getExamples());
        System.out.println("Follow-up questions: " + daily.getNotes());
        System.out.println("\n" + daily.stringAnswers());

        // ADDINg a comment 
        com.fwproblemsolversite.problems.Comment comment =
        new com.fwproblemsolversite.problems.Comment(
            jimmy.getId(),
            daily.getId(),
            "Im confused on the second solution. Can someone explain?"
        );
        daily.addComment(comment);
        //display comment
        System.out.println(
            jimmy.getUsername() + " (" + comment.getDate() + "): " + comment.getCommentText()
        );
        try{
            java.io.PrintWriter writer = new java.io.PrintWriter("jimmy_problem.txt");
            
            writer.println("===== Daily Challenge =====");
            writer.println("Title: " + daily.getTitle());
            writer.println("Description: " + daily.getDescription());
            writer.println("Difficulty: " + daily.getDifficulty());
            writer.println("Tags: " + daily.getTags());
            writer.println(daily.stringAnswers());

            writer.close();
            System.out.println("Problem exported to jimmy_problem.txt");
        }catch (Exception e){
            System.out.println("Error exporting the file");
        }

        System.out.println("\nSearching for: binary search tree questions");

        ArrayList<Problem> foundProblems = new ArrayList<>();

        for (Problem p : app.getAllQuestions()){
            if (p.getTitle().toLowerCase().contains("binary search tree")){
                foundProblems.add(p);   
            }
        }

        if(foundProblems.size() > 0){
            System.out.println("\nFound " + foundProblems.size() + " problms:\n");
            
            for(Problem p : foundProblems){
                System.out.println("Title: " + p.getTitle());
                System.out.println("Difficulty: " + p.getDifficulty());
            }
        }else{
            System.out.println("No problems were found.");
        }

        //INcrease the streak
        jimmy.getProgress().updateProgress(
            "1",
            com.fwproblemsolversite.enums.Difficulty.MEDIUM
        );

        System.out.println("New Streak: " + jimmy.getProgress().getDailyStreak());

        app.logout();
        System.out.println("Jimmy logged out.");
        }



        //Test the getAllQuestions function to ensure that the questions are being loaded correctly.
        ArrayList<Problem> questions = app.getAllQuestions();

        if (questions != null && questions.size() > 0) {

            System.out.println("Questions successfully loaded.");
            System.out.println("Total questions loaded: " + questions.size());

        } else {
            System.out.println("No questions were loaded.");
        }

        //Test the createAccount function to ensure that new accounts can be created successfully.
        if (app.createAccount("John", "Smith", "jsmith", "jsmith@email.com", "pass123",
            com.fwproblemsolversite.enums.AccountType.STUDENT
        )) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }

        //Test the createAccount function with an empty username to ensure that account creation fails.
        if (app.createAccount("Jane", "Smith", "", "jane@email.com", "pass123",
            com.fwproblemsolversite.enums.AccountType.STUDENT
        )) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account creation failed.");
        }

        //Logout the user to ensure that the logout function works correctly.
        app.logout();

        com.fwproblemsolversite.io.dataWriter writer =
        new com.fwproblemsolversite.io.dataWriter();

        writer.saveAccounts(app.getAccountData().getAccounts());
        writer.saveProblems(app.getAllQuestions());
        
    }
    
}
