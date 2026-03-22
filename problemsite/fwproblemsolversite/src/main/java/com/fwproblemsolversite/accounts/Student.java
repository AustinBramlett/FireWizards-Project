package com.fwproblemsolversite.accounts;

import java.util.UUID;

import com.fwproblemsolversite.enums.AccountType;
/**
 * Represents a studentn account in the system.
 * Students can view and solve the problems in the system,
 * and they can submit answers to the problems.
 */
public class Student extends Account {
    /**
     * Shows the rank of the student in the system.
     */
    private String rank;
    /**
     * Creates a Student account with basic user information.
     * 
     * @param id The unique identifier for the account.
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param username The username for login.
     * @param email The email address associated with the account.
     * @param password The password for the account.
     */
    public Student(UUID id, String firstName, String lastName, String username,
                   String email, String password) {
        super(id, firstName, lastName, username, email, password);
    }
    /**
     * Allows the student to view a problem in the system.
     * 
     * @param problem The problem to be viewed.
     */
    public void viewProblem(Object problem) {
    }
    /**
     * Starts a timer for the problem.
     */
    public void startTimer() {
    }
    /**
     * Submits an answer for a problem.
     * 
     * @param code The code submitted by the student.
     * @param result The result of the code submission.
     */
    public void submit(String code, String result) {
    }
    /**
     * Returns the Account type for the user.
     * 
     * @return The AccountType of the user.
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.STUDENT;
    }
}