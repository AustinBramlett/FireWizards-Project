package com.fwproblemsolversite;

import java.util.ArrayList;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.drivers.dataLoader;
import com.fwproblemsolversite.problems.Problem;

// Main application class for the problem-solving platform.
public class ProblemApplication {

    private AccountData accountData;
    private ProblemData problemData;

    // Constructor to initialize the application and load user data.
    public ProblemApplication() {
        accountData = AccountData.getInstance();
        accountData.setAccounts(dataLoader.LoadAccounts());

        problemData = new ProblemData();
        problemData.getProblems().addAll(dataLoader.LoadProblems());
    }

    // Login Method
    public Account login(String username, String password) {
        Account account = accountData.getAccountByUsername(username);

        if (account != null && account.getPassword().equals(password)) {
            return account;
        }

        return null;
    }

    // Get Questions Method
    public ArrayList<Problem> getAllQuestions() {
        return problemData.getProblems();
    }

    public AccountData getAccountData() {
        return accountData;
    }
}