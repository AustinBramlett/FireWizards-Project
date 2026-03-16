package com.fwproblemsolversite.accounts;

import java.util.ArrayList;
import java.util.UUID;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.enums.AccountType;

public class Contributor extends Account {

    private ArrayList<UUID> questionsMade;
    public Contributor(UUID id, String firstName, String lastName, String username,
                   String email, String password, ArrayList<UUID> questionsMade) {
        super(id, firstName, lastName, username, email, password);
        this.questionsMade = questionsMade;
    }

    public boolean addProblem(String title, String description,
                              ArrayList<String> constraints,
                              Language language,
                              ArrayList<String[]> examples,
                              ArrayList<String> notes,
                              ProblemType type,
                              ArrayList<String> tags,
                              double timer,
                              String answer) {
        return false;
    }

    public boolean removeProblem(UUID problemID) {
        return false;
    }
    @Override
    public AccountType getAccountType() {
        return AccountType.CONTRIBUTOR;
    }
}