package com.fwproblemsolversite;

import java.util.ArrayList;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Account;
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
    }
}