package com.fwproblemsolversite.io;

import java.util.ArrayList;
import java.util.List;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.accounts.Administrator;
import com.fwproblemsolversite.accounts.Contributor;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.problems.Submission;
import com.fwproblemsolversite.problems.Timer;
import com.model.DataConstants;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;

import java.util.UUID;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

public class dataWriter extends DataConstants{
    private static boolean debug = false;
    /**
     * A simple method to print debug messages if debug mode is on.
     * @param message The message to print.
     */
    private static void debugOut(String message) {
        if(debug) System.out.println("dataWriter: " + message);
    }
    /**
     * A method to turn on debug mode for dataWriter, letting it print debug messages to the console to help with error tracing.
     */
    public static void debugMode() {
        debug = true;
    }
    //Makes everything significantly less messy and easier to edit.
    /**
     * A private method to write any object to JSON, if provided settings exist.
     * @param obj The type of object being saved
     * @param json The jsonobject storing this data
     * @param header The header of the data
     * @param contents The data
     * @return True if it worked, false if it didn't.
     */
    private static boolean writeToJSON(Object obj, JSONObject json, String header, Object contents) {
        if(obj == null || json == null || header == null){
            System.out.println("A field outside of constraints was null! Check your code and try again.");
            return false;
        }
        switch(obj){
            //Case for all Account objects.
            case Account a:
                switch(header){
                    case CONTRIBUTOR_QUESTIONS_MADE:
                        if(contents == null) {
                            debugOut("Contributor " + json.get(ACCOUNT_USER_NAME) + " has no questions made log specified!");
                            return false;
                        }
                        ArrayList<String> questionsMade = new ArrayList<String>();
                        for(UUID id : (ArrayList<UUID>) contents){
                            questionsMade.add(id.toString()); //We have to turn the UUIDs into strings to save them in JSON, but this is fine since we can turn them back into UUIDs when we load the data.
                        }
                        json.put(header, questionsMade);
                        return true;
                    case ADMINISTRATOR_TERM_LOG:
                        if(contents == null) {
                            debugOut("Administrator " + json.get(ACCOUNT_USER_NAME) + " has no termination log specified!");
                            return false;
                        }
                        ArrayList<String> termLog = new ArrayList<String>();
                        for(UUID id : (ArrayList<UUID>) contents){
                            termLog.add(id.toString());
                        }
                        json.put(header, termLog);
                        return true;
                    case ADMINISTRATOR_BAN_LOG:
                        if(contents == null) {
                            debugOut("Administrator " + json.get(ACCOUNT_USER_NAME) + " has no ban log specified!");
                            return false;
                        }
                        ArrayList<String> banLog = new ArrayList<String>();
                        for(Administrator.ban ban : (ArrayList<Administrator.ban>) contents){
                            banLog.add(ban.accountID().toString());
                        }
                        json.put(header, banLog);
                        return true;
                    case ADMINISTRATOR_BAN_END_DATES:
                        if(contents == null) {
                            debugOut("Administrator " + json.get(ACCOUNT_USER_NAME) + " has no ban end dates specified!");
                            return false;
                        }
                        ArrayList<String> banEndDates = new ArrayList<String>();
                        for(Administrator.ban ban : (ArrayList<Administrator.ban>) contents){
                            banEndDates.add(ban.date().toString());
                        }
                        json.put(header, banEndDates);
                        return true;
                    case ADMINISTRATOR_MUTE_LOG:
                        if(contents == null) {
                            debugOut("Administrator " + json.get(ACCOUNT_USER_NAME) + " has no mute log specified!");
                            return false;
                        }
                        ArrayList<String> muteLog = new ArrayList<String>();
                        for(Administrator.mute mute : (ArrayList<Administrator.mute>) contents){
                            muteLog.add(mute.accountID().toString());
                        }
                        json.put(header, muteLog);
                        return true;
                    case ADMINISTRATOR_MUTE_END_DATES:
                        if(contents == null) {
                            debugOut("Administrator " + json.get(ACCOUNT_USER_NAME) + " has no mute end dates specified!");
                            return false;
                        }
                        ArrayList<String> muteEndDates = new ArrayList<String>();
                        for(Administrator.mute mute : (ArrayList<Administrator.mute>) contents){
                            muteEndDates.add(mute.date().toString());
                        }
                        json.put(header, muteEndDates);
                        return true;
                    case ACCOUNT_PROGRESS:
                        if(contents == null) {
                            debugOut("An Account has no progress!");
                            return false;
                        }
                        json.put(ACCOUNT_PROGRESS, contents);
                        return true;
                    case ACCOUNT_LAST_DATE:
                        if(contents == null){
                            debugOut("An Account has no last active date!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_USER_NAME:
                        if(contents == null) {
                            debugOut("An Account has no username! (!!!)");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_PASSWORD:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no password specified!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_EMAIL:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no email specified!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_TYPE:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no account type specified!");
                            return false;
                        }
                        //Account type must be specified! This switch is just to make sure it's valid and to prevent unreadable content in the JSON file.
                        String newContents;
                        switch(contents) {
                            case String j:
                                newContents = contents.toString(); //Making sure the object is a string, and making sure my IDE doesn't yell at me!
                                break;
                            default:
                                return false;
                        }
                        switch(newContents) {
                            case "ADMIN":
                                json.put(header, contents);
                                return true;
                            case "STUDENT":
                                json.put(header, contents);
                                return true;
                            case "CONTRIBUTOR":
                                json.put(header, contents);
                                return true;
                            default:
                                debugOut("Invalid account type detected for " + json.get(ACCOUNT_USER_NAME) + "!");
                                return false;
                        }
                    case ITEM_ID:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no ID!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_FIRST_NAME:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no first name specified!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_LAST_NAME:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no last name specified!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ACCOUNT_MUTED:
                        if(contents == null) {
                            debugOut("Account " + json.get(ACCOUNT_USER_NAME) + " has no muted status specified!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    default:
                        debugOut("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            //Case for all Problem objects.
            case Problem b:
                switch(header){
                    case PROBLEM_TITLE:
                        if(contents == null) {
                            debugOut("Problem has no title!");
                            return false;
                        }
                        json.put(header, contents);
                        return true;
                    case ITEM_ID:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no ID!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_EXAMPLES:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no examples!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_ANSWERS:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no answers!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_COMMENTS:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no comments array!");
                            return false;
                        } else if (!(contents instanceof ArrayList)){
                            debugOut("Comments must be provided as an ArrayList of Comments!");
                            return false;
                        } else {
                            JSONArray commentsArray = new JSONArray();
                            for(Comment comment : (ArrayList<Comment>) contents){
                                commentsArray.add(commentToJSONObject(comment));
                            }
                            json.put(header, commentsArray);
                            return true;
                        }
                    case PROBLEM_DIFFICULTY:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no difficulty!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_DESCRIPTION:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no description!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_LANGUAGE:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no language specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_NOTES:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no notes!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_SUBMISSIONS:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no submissions array!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_TAGS:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no tags!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_TIMER:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no timer specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_TYPE:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no type!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_CONSTRAINTS:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no constraints!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case PROBLEM_CODE:
                        if(contents == null) {
                            debugOut("Problem " + json.get(PROBLEM_TITLE) + " has no code specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    default:
                        debugOut("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            //Case for all Report objects.
            case Report c:
                switch(header){
                    case ITEM_ID:
                        if(contents == null) {
                            debugOut("Report has no ID!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case REPORT_ACCUSED:
                        if(contents == null) {
                            debugOut("Report " + json.get(ITEM_ID) + " has no accused specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case REPORT_REASON:
                        if(contents == null) {
                            debugOut("Report " + json.get(ITEM_ID) + " has no reason specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    case REPORT_SENDER:
                        if(contents == null) {
                            debugOut("Report " + json.get(ITEM_ID) + " has no sender specified!");
                            return false;
                        } else {
                            json.put(header, contents);
                            return true;
                        }
                    default:
                        debugOut("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            default:
                System.out.println("Invalid object type. Set one up in writeToJSON!");
                return false;
        }
    }
    /**
    * Converts a single Comment to a JSONObject to be saved to a JSONArray in dataWriter.
     * @param comment The Comment to convert.
     * @return JSONObject representing the Comment.
    */
    private static JSONObject commentToJSONObject(Comment comment) {
        if (comment == null) return null;
        JSONObject jsonComment = new JSONObject();
        jsonComment.put(COMMENT_TEXT, comment.getCommentText());
        jsonComment.put(COMMENT_SENDER, comment.getSender() != null ? comment.getSender().toString() : null);
        jsonComment.put(COMMENT_SCORE, comment.getScore());
        jsonComment.put(COMMENT_DATE, comment.getDate() != null ? comment.getDate().toString() : null);

        //Handling replies recursively
        ArrayList<Comment> replies = comment.getReplies();
        if (replies != null && !replies.isEmpty()) {
            JSONArray repliesArray = new JSONArray();
            for (Comment reply : replies) {
                repliesArray.add(commentToJSONObject(reply));  //Recursive call
            }
            jsonComment.put(COMMENT_REPLIES, repliesArray);
        } else {
            jsonComment.put(COMMENT_REPLIES, new JSONArray());  //Empty array if no replies
        }

        return jsonComment;
    }
    /**
     * Saves all accounts to accounts.json.
     * @param accounts The account arraylist to save
     * @return Success = true, failure = false
     */
    public static boolean saveAccounts(ArrayList<Account> accounts) {
        String fileName = ACCOUNT_FILE_NAME;
        if(isJUnitTest()) fileName = ACCOUNT_TEMP_FILE_NAME;
        try(FileWriter writer = new FileWriter(fileName)){
            JSONArray accountsFile = new JSONArray();
            for(Account account : accounts){
                JSONObject item = new JSONObject();
                if(account instanceof Administrator){
                    writeToJSON(account, item, ADMINISTRATOR_BAN_LOG, ((Administrator) account).getBans());
                    writeToJSON(account, item, ADMINISTRATOR_MUTE_LOG, ((Administrator) account).getMutes());
                    writeToJSON(account, item, ADMINISTRATOR_BAN_END_DATES, ((Administrator) account).getBans());
                    writeToJSON(account, item, ADMINISTRATOR_MUTE_END_DATES, ((Administrator) account).getMutes());
                    writeToJSON(account, item, ADMINISTRATOR_TERM_LOG, ((Administrator) account).getTermLog());
                }
                if(account instanceof Contributor){
                    writeToJSON(account, item, CONTRIBUTOR_QUESTIONS_MADE, ((Contributor) account).getQuestionsMade());
                }
                writeToJSON(account, item, ACCOUNT_USER_NAME, account.getUsername());
                //The following ternary is important for getting anything that needs a toString as strings without throwing an error (NullPointerException).
                //The format is simple: obj.getItem() != null ? obj.getItem().toString() : null.
                //By doing this we can tell writeToJSON if an object is null without throwing a NPE by trying to turn it into a String.
                writeToJSON(account, item, ACCOUNT_TYPE, account.getAccountType() != null ? account.getAccountType().toString() : null);
                writeToJSON(account, item, ACCOUNT_EMAIL, account.getEmail());
                writeToJSON(account, item, ACCOUNT_PASSWORD, account.getPassword());
                writeToJSON(account, item, ITEM_ID, account.getId() != null ? account.getId().toString() : null);
                writeToJSON(account, item, ACCOUNT_FIRST_NAME, account.getFirstName());
                writeToJSON(account, item, ACCOUNT_LAST_NAME, account.getLastName());
                writeToJSON(account, item, ACCOUNT_MUTED, account.isMuted() ? "true" : "false");
                writeToJSON(account, item, ACCOUNT_PROGRESS, account.getProgress().getProgressDataList());
                writeToJSON(account, item, ACCOUNT_LAST_DATE, account.getProgress().getLastActiveString());
                accountsFile.add(item);
                debugOut("dataWriter(saveAccounts): Successfully saved account " + account.getUsername() + "!");
                debugOut("dataWriter(saveAccounts): Contents: " + item.toJSONString());
            }
            writer.write(accountsFile.toJSONString());
            writer.close(); //We might want to switch to BufferedWriter? It's fine for now though.
        } catch(IOException e){
            e.printStackTrace();
            return false; //Something definitely went wrong here!
        }
        return accounts != null; //If there's nothing saved, something probably went wrong
    }
    /**
     * Saves all problems to problems.json
     * @param problems The array of problems being saved
     * @return Success= true, failure= false
     */
    public static boolean saveProblems(ArrayList<Problem> problems) {
        String fileName = PROBLEM_FILE_NAME;
        if(isJUnitTest()) fileName = PROBLEM_TEMP_FILE_NAME;
        try(FileWriter writer = new FileWriter(fileName)){
            JSONArray problemsFile = new JSONArray();
            for(Problem problem : problems){
                JSONObject item = new JSONObject();
                writeToJSON(problem, item, PROBLEM_TITLE, problem.getTitle());
                writeToJSON(problem, item, ITEM_ID, problem.getID() != null ? problem.getID().toString() : null);
                writeToJSON(problem, item, PROBLEM_DESCRIPTION, problem.getDescription());
                writeToJSON(problem, item, PROBLEM_DIFFICULTY, problem.getDifficulty() != null ? problem.getDifficulty().toString() : null);
                writeToJSON(problem, item, PROBLEM_LANGUAGE, problem.getLanguage() != null ? problem.getLanguage().toString() : null);
                writeToJSON(problem, item, PROBLEM_NOTES, problem.getNotes() != null ? problem.getNotes() : null);
                writeToJSON(problem, item, PROBLEM_EXAMPLES, problem.getExamples() != null ? problem.getExamples() : null);
                writeToJSON(problem, item, PROBLEM_SUBMISSIONS, problem.getSubmissions() != null ? problem.getSubmissionsArray() : null);
                writeToJSON(problem, item, PROBLEM_TAGS, problem.getTags() != null ? problem.getTags() : null);
                //Noteworthy exception to our formula from above for timer since it's a Double and not an enum or array of some kind.
                //We still want to save it as a String though to prevent errors. This requires String.valueOf(...) instead of (...).toString().
                writeToJSON(problem, item, PROBLEM_TIMER, problem.getTimer() != null ? String.valueOf(problem.getTimer().toDouble()) : null);
                writeToJSON(problem, item, PROBLEM_TYPE, problem.getType() != null ? problem.getType().toString() : null);
                writeToJSON(problem, item, PROBLEM_CONSTRAINTS, problem.getConstraints() != null ? problem.getConstraints() : null);
                writeToJSON(problem, item, PROBLEM_COMMENTS, problem.getComments());
                writeToJSON(problem, item, PROBLEM_ANSWERS, problem.getAnswers() != null ? problem.getAnswers() : null);
                writeToJSON(problem, item, PROBLEM_CODE, problem.getCode() != null ? problem.getCode() : null);
                problemsFile.add(item);
                debugOut("dataWriter(saveProblems): Successfully saved problem " + problem.getID() + "!");
                debugOut("dataWriter(saveProblems): Contents: " + item.toJSONString());
            }
            writer.write(problemsFile.toJSONString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return problems != null;
    }
    /**
     * Saves all reports to reports.json
     * @param reports The report array list being saved
     * @return Success status (boolean)
     */
    public static boolean saveReports(ArrayList<Report> reports) {
        String fileName = REPORT_FILE_NAME;
        if(isJUnitTest()) fileName = REPORT_TEMP_FILE_NAME;
        try(FileWriter writer = new FileWriter(fileName)){
            JSONArray reportsFile = new JSONArray();
            for(Report report : reports){
                JSONObject item = new JSONObject();
                writeToJSON(report, item, ITEM_ID, report.getID() != null ? report.getID().toString() : null);
                writeToJSON(report, item, REPORT_REASON, report.getReason());
                writeToJSON(report, item, REPORT_ACCUSED, report.getAccused());
                writeToJSON(report, item, REPORT_SENDER, report.getSender());
                reportsFile.add(item);
                debugOut("dataWriter(saveReports): Successfully saved report " + report.getID() + "!");
                debugOut("dataWriter(saveReports): Contents: " + item.toJSONString());
            }
            writer.write(reportsFile.toJSONString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return reports != null;
    }
    /**
     * Main method for testing and writing
     */
    public static void main(String[] args) {
        //This is just for testing purposes.
        ArrayList<Account> accounts = dataLoader.LoadAccounts();
        ArrayList<Problem> problems = dataLoader.LoadProblems();
        ArrayList<Report> reports = dataLoader.LoadReports();
        ArrayList<String> constraints = new ArrayList<>(List.of("No", "Yes"));
        ArrayList<String> tags = new ArrayList<>(List.of("Yes", "No", "Maybe"));
        Timer timer = new Timer(30.0);
        Comment commentOne = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Blah blah blah...");
        Comment commentTwo = new Comment(UUID.randomUUID(), UUID.randomUUID(), "Yada yada yada...");
        ArrayList<Comment> comments = new ArrayList<>(List.of(commentOne, commentTwo));
        Submission submissionOne = new Submission("HHHH", "AAAA", UUID.randomUUID().toString());
        Submission submissionTwo = new Submission("HAHH", "AHAA", UUID.randomUUID().toString());
        ArrayList<Submission> submissions = new ArrayList<>(List.of(submissionOne, submissionTwo)); 
        ArrayList<String> notes = new ArrayList<>(List.of("AAA", "BBB"));
        ArrayList<String> example = new ArrayList<>(List.of("AAAA", "BBBB"));
        ArrayList<String> example2 = new ArrayList<>(List.of("AAA", "BBB"));

        ArrayList<ArrayList<String>> examples = new ArrayList<>(List.of(example, example2));
        saveAccounts(accounts);
        saveProblems(problems);
        saveReports(reports);
    }
}
