package com.fwproblemsolversite.drivers;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.enums.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;
import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;

public class dataLoader {
    private static JSONParser parser = new JSONParser();
    public static ArrayList<Report> LoadReports(){
        ArrayList<Report> reports = new ArrayList<>();
        try (FileReader reader = new FileReader("reports.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Parse the JSON and create Report instances
            String id = (String) jsonObject.get("id");
            String reason = (String) jsonObject.get("reason");
            String accused = (String) jsonObject.get("accused");
            String sender = (String) jsonObject.get("sender");
            Report report = new Report(reason, accused, sender, id);
            reports.add(report);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return reports; // Return the list of reports
    }
    public static ArrayList<Problem> LoadProblems(){
        ArrayList<Problem> problems = new ArrayList<>();
        try (FileReader reader = new FileReader("problems.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Parse the JSON and create Problem instances
            UUID id = UUID.fromString((String) jsonObject.get("problemID"));
            String title = (String) jsonObject.get("title");
            String description = (String) jsonObject.get("description");
            String difficulty = (String) jsonObject.get("difficulty");
            String type = (String) jsonObject.get("type");
            JSONArray tagsArray = (JSONArray) jsonObject.get("tags");
            ArrayList<String> tags = new ArrayList<>();
            for (Object tag : tagsArray) {
                tags.add((String) tag);
            }
            String answer = (String) jsonObject.get("answer");
            JSONArray commentsArray = (JSONArray) jsonObject.get("comments");
            ArrayList<Comment> comments = new ArrayList<>();
            for (Object comment : commentsArray) {
                JSONObject commentObj = (JSONObject) comment;
                String commentText = (String) commentObj.get("commentText");
                UUID sender = UUID.fromString((String) commentObj.get("sender"));
                int score = ((Long) commentObj.get("score")).intValue();
                Comment commentInstance = new Comment(sender, commentText, score);
                comments.add(commentInstance);
            }
            int timer = ((Long) jsonObject.get("timer")).intValue();
            Problem problem = new Problem(title, id, description, null, null, null, null, 
                null, tags, timer, answer);
            //We're missing a lot.
            problems.add(problem);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return problems; // Return the list of problems
    }
    public static ArrayList<Account> LoadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try (FileReader reader = new FileReader("accounts.json")) {
            JSONObject accountObj = (JSONObject) parser.parse(reader);
            // Parse the JSON and create the proper Account instances based on the account type
            String accountType = (String) accountObj.get("accountType");
            //Switch to determine which type of account to create
            switch (accountType) {
                case "STUDENT":
                    // Create a Student account instance
                    break;
                case "ADMINISTRATOR":
                    // Create an Administrator account instance
                    break;
                case "CONTRIBUTOR":
                    // Create a Contributor account instance
                    break;
                default:
                    System.out.println("Unknown account type: " + accountType);
            }
            // Add the created account instance to the accounts list
            accounts.add(accountInstance); 
            //We're missing some info to create the account instance for anybody other than students. We gotta fix that quick.

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts; // Return the list of accounts
    }
}
