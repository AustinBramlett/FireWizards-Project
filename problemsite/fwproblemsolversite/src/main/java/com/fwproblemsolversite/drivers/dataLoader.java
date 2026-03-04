package com.fwproblemsolversite.drivers;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.accounts.Administrator;
import com.fwproblemsolversite.accounts.Contributor;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.accounts.Student;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;

public class dataLoader {
    private static JSONParser parser = new JSONParser();
    public static ArrayList<Report> LoadReports(){
        ArrayList<Report> reports = new ArrayList<>();
        try (FileReader reader = new FileReader("reports.json")) {
            JSONArray jsonObject = (JSONArray) parser.parse(reader);
            for(Object obj : jsonObject) {
                JSONObject reportObj = (JSONObject) obj;
                // Parse the JSON and create Report instances
                String id = (String) reportObj.get("id");
                String reason = (String) reportObj.get("reason");
                String accused = (String) reportObj.get("accused");
                String sender = (String) reportObj.get("sender");
                Report report = new Report(reason, accused, sender, id);
                reports.add(report);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return reports; // Return the list of reports
    }
    public static ArrayList<Problem> LoadProblems(){
        ArrayList<Problem> problems = new ArrayList<>();
        try (FileReader reader = new FileReader("problems.json")) {
            JSONArray jsonObject = (JSONArray) parser.parse(reader);
            for(Object obj : jsonObject) {
                JSONObject problemObj = (JSONObject) obj;
                // Parses the JSON and create Problem instances.
                UUID id = UUID.fromString((String) problemObj.get("problemID"));
                String title = (String) problemObj.get("title");
                String description = (String) problemObj.get("description");
                String difficultyString = (String) problemObj.get("difficulty");
                Difficulty difficulty;
                switch(difficultyString) {
                    case "EASY":
                        difficulty = Difficulty.EASY;
                        break;
                    case "MEDIUM":
                        difficulty = Difficulty.MEDIUM;
                        break;
                    case "HARD":
                        difficulty = Difficulty.HARD;
                        break;
                    default:
                        System.out.println("Unknown difficulty: " + difficultyString);
                        difficulty = null;
                }
                String typeString = (String) problemObj.get("type");
                ProblemType type;
                switch(typeString) {
                    case "ARRAY":
                        type = ProblemType.ARRAY;
                        break;
                    case "STRING":
                        type = ProblemType.STRING;
                        break;
                    case "TREE":
                        type = ProblemType.TREE;
                        break;
                    case "STACK":
                        type = ProblemType.STACK;
                        break;
                    case "QUEUE":
                        type = ProblemType.QUEUE;
                        break;
                    case "LINKEDLIST":
                        type = ProblemType.LINKEDLIST;
                        break;
                    case "HASHMAP":
                        type = ProblemType.HASHMAP;
                        break;
                    default:
                        System.out.println("Unknown problem type: " + typeString);
                        type = null;
                }
                JSONArray tagsArray = (JSONArray) problemObj.get("tags");
                ArrayList<String> tags = new ArrayList<>();
                for (Object tag : tagsArray) {
                    tags.add((String) tag);
                }
                String answer = (String) problemObj.get("answer");
                JSONArray commentsArray = (JSONArray) problemObj.get("comments");
                ArrayList<Comment> comments = new ArrayList<>();
                for (Object comment : commentsArray) {
                    JSONObject commentObj = (JSONObject) comment;
                    String commentText = (String) commentObj.get("commentText");
                    UUID sender = UUID.fromString((String) commentObj.get("sender"));
                    int score = ((Long) commentObj.get("score")).intValue();
                    Comment commentInstance = new Comment(sender, commentText, score);
                    comments.add(commentInstance);
                }
                double timer = ((Long) problemObj.get("timer")).intValue();
                Problem problem = new Problem(title, id, description, null, null, null, null, 
                    type, tags, timer, answer, difficulty);
                //We're missing a lot.
                problems.add(problem);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return problems; // Return the list of problems
    }
    public static ArrayList<Account> LoadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
        InputStream input = dataLoader.class.getClassLoader()
            .getResourceAsStream("accounts.json");

        JSONArray jsonObj = (JSONArray) parser.parse(
             new InputStreamReader(input));

            // Parse the JSON and create the proper Account instances based on the account type
            for(Object obj : jsonObj) {
                JSONObject accountObj = (JSONObject) obj;
                String accountType = (String) accountObj.get("accountType");
                //Switch to determine which type of account to create
                Account accountInstance;
                //Every account has the follolwing:
                UUID id = UUID.fromString((String) accountObj.get("id"));
                String firstName = (String) accountObj.get("firstName");
                String lastName = (String) accountObj.get("lastName");
                String username = (String) accountObj.get("username");
                String email = (String) accountObj.get("email");
                String password = (String) accountObj.get("password");
                switch (accountType) {
                    case "STUDENT":
                        accountInstance = new Student(id, firstName, lastName, username, email, password);
                        break;
                    case "ADMINISTRATOR":
                        //logs are currently not in the json, so they'll be empty for now. We don't need them at the moment..
                        ArrayList<UUID> adminBanLog = new ArrayList<>();
                        ArrayList<UUID> adminMuteLog = new ArrayList<>();
                        ArrayList<String> adminTermLog = new ArrayList<>();
                        accountInstance = new Administrator(id, firstName, lastName, username, email, password, adminBanLog, adminMuteLog, adminTermLog);
                        break;
                    case "CONTRIBUTOR":
                        ArrayList<UUID> questionsMade = new ArrayList<>();
                        accountInstance = new Contributor(id, firstName, lastName, username, email, password, questionsMade);
                        break;
                    default:
                        System.out.println("Unknown account type: " + accountType);
                        accountInstance = null;
                }
                // Add the created account instance to the accounts list
                accounts.add(accountInstance); 
                //We're missing some info to create the account instance for anybody other than students. We'll have to fix that soon.
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts; // Return the list of accounts
    }
}
