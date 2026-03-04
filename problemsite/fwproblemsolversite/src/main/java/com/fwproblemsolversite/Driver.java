package com.fwproblemsolversite;

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
    }
}