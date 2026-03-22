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
import com.fwproblemsolversite.problems.Submission;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Language;

public class dataLoader {
    private static JSONParser parser = new JSONParser();
    public static ArrayList<Report> LoadReports(){
        ArrayList<Report> reports = new ArrayList<>();
        try (FileReader reader = new FileReader("problemsite\\fwproblemsolversite\\target\\classes\\jsonSamples\\reports.json")) {
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
        try (FileReader reader = new FileReader("problemsite\\fwproblemsolversite\\target\\classes\\jsonSamples\\problems.json")) {
            JSONArray jsonObject = (JSONArray) parser.parse(reader);
            for(Object obj : jsonObject) {
                JSONObject problemObj = (JSONObject) obj;
                // Parses the JSON and create Problem instances.
                UUID id = (String) problemObj.get("problemID") != null ? UUID.fromString((String) problemObj.get("problemID")) : null;
                String title = (String) problemObj.get("title");
                String description = (String) problemObj.get("description");
                String difficultyString = (String) problemObj.get("difficulty");
                Difficulty difficulty;
                if(title == null) {
                    System.out.println("dataLoader(LoadProblems): Title not specified for a problem, skipping to prevent further error!");
                    continue; // Skip this problem since title is essential
                }
                if(difficultyString == null) {
                    System.out.println("dataLoader(LoadProblems): Difficulty not specified for problem " + title);
                    difficulty = null;
                } else {
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
                        System.out.println("dataLoader(LoadProblems): Unknown difficulty: " + difficultyString);
                        difficulty = null;
                    }
                }
                String typeString = (String) problemObj.get("type");
                ProblemType type = null;
                if(typeString == null) {
                    System.out.println("dataLoader(LoadProblems): Problem type not specified for problem " + title);
                } else {
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
                        System.out.println("dataLoader(LoadProblems): Unknown problem type: " + typeString);
                        type = null;
                    }
                }
                String languageString = (String) problemObj.get("language");
                Language language = null;
                if(languageString ==null) {
                    System.out.println("dataLoader(LoadProblems): Language not specified for problem " + title);
                } else {
                    switch(languageString) {
                    case "JAVA":
                        language = Language.JAVA;
                        break;
                    case "PYTHON":
                        language = Language.PYTHON;
                        break;
                    case "CPP":
                        language = Language.CPP;
                        break;
                    default:
                        System.out.println("dataLoader(LoadProblems): Unknown language: " + languageString);
                    }
                }
                JSONArray tagsArray = (JSONArray) problemObj.get("tags");
                JSONArray constraintsArray = (JSONArray) problemObj.get("constraints");
                JSONArray examplesArray = (JSONArray) problemObj.get("examples");
                JSONArray notesArray = (JSONArray) problemObj.get("notes");
                JSONArray commentsArray = (JSONArray) problemObj.get("comments");
                JSONArray submissionsArray = (JSONArray) problemObj.get("submissions");
                String answer = (String) problemObj.get("answer");
                ArrayList<String> tags = new ArrayList<>();
                if(tagsArray != null) {
                    for (Object tag : tagsArray) {
                        tags.add((String) tag);
                    }
                } else {
                    System.out.println("dataLoader(LoadProblems): No tags specified for problem " + title);
                }
                ArrayList<String> constraints = new ArrayList<>();
                if(constraintsArray != null) {
                    for (Object constraint : constraintsArray) {
                        constraints.add((String) constraint);
                    }
                } else {
                    System.out.println("dataLoader(LoadProblems): No constraints specified for problem " + title);
                }
                ArrayList<ArrayList<String>> examples = new ArrayList<>();
                if(examplesArray != null) {
                    for (Object example : examplesArray) {
                        JSONArray exampleObj = (JSONArray) example;
                        examples.add((ArrayList<String>) example);
                    }
                } else {
                    System.out.println("dataLoader(LoadProblems): No examples specified for problem " + title);
                }
                ArrayList<String> notes = new ArrayList<>();
                if(notesArray != null) {
                    for (Object note : notesArray) {
                        notes.add((String) note);
                    }
                } else {
                    System.out.println("dataLoader(LoadProblems): No notes specified for problem " + title);
                }
                ArrayList<Comment> comments = new ArrayList<>();
                if(commentsArray != null) {   
                    for (Object comment : commentsArray) {
                        JSONObject commentObj = (JSONObject) comment;
                        String commentText = (String) commentObj.get("commentText");
                        UUID problemID = UUID.fromString((String) commentObj.get("problemID"));
                        UUID sender = UUID.fromString((String) commentObj.get("sender"));
                        int score = ((Long) commentObj.get("score")).intValue();
                        ArrayList<Comment> replies = new ArrayList();
                        String date = (String) commentObj.get("date");
                        Comment commentInstance = new Comment(sender, problemID, commentText, score, replies, date);
                        System.out.println(commentInstance);
                        comments.add(commentInstance);
                    }
                } else {
                    System.out.println("dataLoader(LoadProblems): No comments specified for problem " + title);
                }
                ArrayList<Submission> submissions = new ArrayList<>();
                if(submissionsArray != null){
                    for(Object submission : submissionsArray){
                        JSONArray submissionObj = (JSONArray) submission;
                        Submission newSubmission = new Submission((ArrayList<String>) submission); 
                        submissions.add(newSubmission);
                    }
                }else{
                    System.out.println("dataLoader(LoadProblems): No submissions array specified for problem " + title);
                }
                String timerVal = (String) problemObj.get("timer");
                double timer = Double.parseDouble(timerVal);
                Problem problem = new Problem(title, id, description, constraints, language, examples, notes, 
                    type, tags, timer, answer, difficulty, comments, submissions);
                //We're missing submissions and comments at the moment.
                problems.add(problem);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return problems; // Return the list of problems
    }
    public static ArrayList<Account> LoadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try (FileReader reader = new FileReader("problemsite\\fwproblemsolversite\\target\\classes\\jsonSamples\\accounts.json")) {
            JSONArray jsonObj = (JSONArray) parser.parse(reader);
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
                if(accountType == null){
                    System.out.println("dataLoader(LoadAccounts): Type not specified for this account, skipping to prevent further error!");
                } else {
                    switch (accountType) {
                    case "STUDENT":
                        accountInstance = new Student(id, firstName, lastName, username, email, password);
                        break;
                    case "ADMIN":
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
                        System.out.println("dataLoader(LoadAccounts): Unknown account type: " + accountType);
                        accountInstance = null;
                    }
                     // Add the created account instance to the accounts list
                    accounts.add(accountInstance); 
                    //We're missing some info to create the account instance for anybody other than students. We'll have to fix that soon.
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts; // Return the list of accounts
    }
    public static void main(String[] args) {
        ArrayList<Account> accounts = LoadAccounts();
        ArrayList<Problem> problems = LoadProblems();
        ArrayList<Report> reports = LoadReports();
        // Print loaded data for verification
        System.out.println("Loaded Accounts:");
        for (Account account : accounts) {
            System.out.println(account.getUsername() + " (" + account.getPassword() + ", " + account.getEmail() + ")");
        }
        System.out.println("\nLoaded Problems:");
        for (Problem problem : problems) {
            System.out.println(problem.getTitle() + " - " + problem.getDifficulty());
        }
        System.out.println("\nLoaded Reports:");
        for (Report report : reports) {
            System.out.println(report.getReason() + " - " + report.getAccused());
        }
    }
}
