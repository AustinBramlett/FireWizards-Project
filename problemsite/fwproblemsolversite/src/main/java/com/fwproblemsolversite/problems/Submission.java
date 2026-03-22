package com.fwproblemsolversite.problems;

import java.util.ArrayList;
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
    public Submission(ArrayList<String> data){
        this.code = data.get(0);
        this.output = data.get(1);
        this.owner = UUID.fromString(data.get(2));
    }
    public boolean submitAnswer() {
        return false;
    }
    public ArrayList<String> asArrayList() {
        ArrayList<String> thisList = new ArrayList<String>();
        thisList.add(code);
        thisList.add(output);
        thisList.add(owner.toString());
        return thisList;
    }
}