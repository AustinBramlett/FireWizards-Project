package com.fwproblemsolversite.accounts;

import java.util.UUID;

import com.fwproblemsolversite.enums.AccountType;

public class Student extends Account {

    private String rank;

    public Student(UUID id, String firstName, String lastName, String username,
                   String email, String password) {
        super(id, firstName, lastName, username, email, password);
    }

    public void viewProblem(Object problem) {
    }

    public void startTimer() {
    }

    public void submit(String code, String result) {
    }
    /**
     * Returns the AccountType of this instance.
     * @return STUDENT
     */
    @Override
    public AccountType getAccountType(){
        return AccountType.STUDENT;
    }
}