package com.fwproblemsolversite.drivers;

import java.util.ArrayList;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Report;

public class dataWriter {
    public static boolean saveAccounts(ArrayList<Account> accounts) {
        return accounts != null;
    }
    public static boolean saveProblems(ArrayList<Problem> problems) {
        return problems != null;
    }
    public static boolean saveReports(ArrayList<Report> reports) {
        return reports != null;
    }
}
