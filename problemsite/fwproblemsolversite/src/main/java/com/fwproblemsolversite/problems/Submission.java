package com.fwproblemsolversite.problems;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Represents a users submission to a problem in the system.
 * 
 * Stores the submission, output, and user who made the submission.
 */
public class Submission {

    private String code;
    private String output;
    private UUID owner;
    /**
     * Creats a submission from values
     * 
     * @param code The code of the submission.
     * @param output The output of the submission.
     * @param owner The UUID of the user who made the submission.
     */
    public Submission(String code, String output, String owner){
        this.code = code;
        this.output = output;
        this.owner = UUID.fromString(owner);
    }
    /**
     * Creates a submission from a list of strings.
     * 
     * @param data the list containing the code, output, and owner of the submission.
     */
    public Submission(ArrayList<String> data){
        this.code = data.get(0);
        this.output = data.get(1);
        this.owner = UUID.fromString(data.get(2));
    }
    /**
     * Submits the answer to the problem and will return true if the answer
     * is correct but false if the answer is not correct.
     * 
     * @return true if the answer is correct, if not then false.
     */
    public boolean submitAnswer() {
        return false;
    }
    /**
     * Converts the submission into a list of strings for JSON files.
     * 
     * @return list containing the code, output, owner ID.
     */
    public ArrayList<String> asArrayList() {
        ArrayList<String> thisList = new ArrayList<String>();
        thisList.add(code);
        thisList.add(output);
        thisList.add(owner.toString());
        return thisList;
    }
}