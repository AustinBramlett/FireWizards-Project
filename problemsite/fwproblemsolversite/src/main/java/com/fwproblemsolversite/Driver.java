package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
//Driver class to test the login function for the system.
public class Driver {

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
    }
}