package com.fwproblemsolversite.problems;

import java.util.UUID;

public class Submission {

    private String code;
    private String output;
    private UUID owner;

    public Submission(String code, String output, String owner){
        this.code = code;
        this.output = output;
        this.owner = UUID.fromString(owner);
    }
    public boolean submitAnswer() {
        return false;
    }
}