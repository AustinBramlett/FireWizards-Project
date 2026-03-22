package com.fwproblemsolversite.drivers;

import java.util.ArrayList;
import java.util.List;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.problems.Submission;
import com.fwproblemsolversite.problems.Timer;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;

import java.util.UUID;
import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

//Note everything here saves to a singular line per object.
public class dataWriter {
    //Makes everything significantly less messy and easier to edit.
    private static boolean writeToJSON(Object obj, JSONObject json, String header, Object contents) {
        if(contents == null) {
            System.out.print("dataWriter(writeToJSON): "); //Traces the error to this method for easier debugging.
        }
        if(obj == null || json == null || header == null){
            System.out.println("A field outside of constraints was null! Check your code and try again.");
            return false;
        }
        switch(obj){
            //Case for all Account objects.
            case Account a:
                switch(header){
                    case "username":
                        if(contents == null) {
                            System.out.println("An Account has no username! (!!!)");
                            return false;
                        } else {
                            json.put("username", contents);
                            return true;
                        }
                    case "password":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no password specified!");
                            return false;
                        } else {
                            json.put("password", contents);
                            return true;
                        }
                    case "email":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no email specified!");
                            return false;
                        } else {
                            json.put("email", contents);
                            return true;
                        }
                    case "type":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no account type specified!");
                            return false;
                        } else {
                            //Account type must be specified! This switch is just to make sure it's valid and to prevent unreadable content in the JSON file.
                            String newContents;
                            switch(contents) {
                                case String j:
                                    newContents = contents.toString(); //I.. I don't even know why I have to do this.
                                    break;
                                default:
                                    return false;
                            }
                            switch(newContents) {
                                case "ADMIN":
                                    json.put("type", "ADMIN");
                                    return true;
                                case "STUDENT":
                                    json.put("type", "STUDENT");
                                    return true;
                                case "CONTRIBUTOR":
                                    json.put("type", "CONTRIBUTOR");
                                    return true;
                                default:
                                    System.out.println("Invalid account type detected for " + json.get("username") + "!");
                                    return false;
                            }
                        }
                    case "id":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no ID!");
                            return false;
                        } else {
                            json.put("id", contents);
                            return true;
                        }
                    case "firstName":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no first name specified!");
                            return false;
                        } else {
                            json.put("firstName", contents);
                            return true;
                        }
                    case "lastName":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no last name specified!");
                            return false;
                        } else {
                            json.put("lastName", contents);
                            return true;
                        }
                    case "muted":
                        if(contents == null) {
                            System.out.println("Account " + json.get("username") + " has no muted status specified!");
                            return false;
                        } else {
                            json.put("muted", contents);
                            return true;
                        }
                    default:
                        System.out.println("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            //Case for all Problem objects.
            case Problem b:
                switch(header){
                    case "title":
                        if(contents == null) {
                            System.out.println("Problem has no title!");
                            return false;
                        } else {
                            json.put("title", contents);
                            return true;
                        }
                    case "id":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no ID!");
                            return false;
                        } else {
                            json.put("id", contents);
                            return true;
                        }
                    case "examples":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no examples!");
                            return false;
                        } else {
                            json.put("examples", contents);
                            return true;
                        }
                    case "comments":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no comments array!");
                            return false;
                        } else {
                            json.put("comments", contents);
                            return true;
                        }
                    case "difficulty":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no difficulty!");
                            return false;
                        } else {
                            json.put("difficulty", contents);
                            return true;
                        }
                    case "description":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no description!");
                            return false;
                        } else {
                            json.put("description", contents);
                            return true;
                        }
                    case "language":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no language specified!");
                            return false;
                        } else {
                            json.put("language", contents);
                            return true;
                        }
                    case "notes":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no notes!");
                            return false;
                        } else {
                            json.put("notes", contents);
                            return true;
                        }
                    case "submissions":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no submissions array!");
                            return false;
                        } else {
                            json.put("submissions", contents);
                            return true;
                        }
                    case "tags":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no tags!");
                            return false;
                        } else {
                            json.put("tags", contents);
                            return true;
                        }
                    case "timer":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no timer specified!");
                            return false;
                        } else {
                            json.put("timer", contents);
                            return true;
                        }
                    case "type":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no type!");
                            return false;
                        } else {
                            json.put("type", contents);
                            return true;
                        }
                    case "constraints":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no constraints!");
                            return false;
                        } else {
                            json.put("constraints", contents);
                            return true;
                        }
                    case "answer":
                        if(contents == null) {
                            System.out.println("Problem " + json.get("title") + " has no answer specified!");
                            return false;
                        } else {
                            json.put("answer", contents);
                            return true;
                        }
                    default:
                        System.out.println("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            //Case for all Report objects.
            case Report c:
                switch(header){
                    case "id":
                        if(contents == null) {
                            System.out.println("Report has no ID!");
                            return false;
                        } else {
                            json.put("id", contents);
                            return true;
                        }
                    case "accused":
                        if(contents == null) {
                            System.out.println("Report " + json.get("id") + " has no accused specified!");
                            return false;
                        } else {
                            json.put("accused", contents);
                            return true;
                        }
                    case "reason":
                        if(contents == null) {
                            System.out.println("Report " + json.get("id") + " has no reason specified!");
                            return false;
                        } else {
                            json.put("reason", contents);
                            return true;
                        }
                    case "sender":
                        if(contents == null) {
                            System.out.println("Report " + json.get("id") + " has no sender specified!");
                            return false;
                        } else {
                            json.put("sender", contents);
                            return true;
                        }
                    default:
                        System.out.println("Invalid header for this object type. Set one up in writeToJSON!");
                        return false;
                }
            default:
                System.out.println("Invalid object type. Set one up in writeToJSON!");
                return false;
        }
    }
    public static boolean saveAccounts(ArrayList<Account> accounts) {
        try(FileWriter writer = new FileWriter("problemsite\\fwproblemsolversite\\src\\main\\resources\\jsonSamples\\accounts.json")){
            for(Account account : accounts){
                JSONObject item = new JSONObject();
                writeToJSON(account, item, "username", account.getUsername());
                //The following ternary is important for getting anything that needs a toString as strings without throwing an error (NullPointerException).
                //The format is simple: obj.getItem() != null ? obj.getItem().toString() : null.
                //By doing this we can tell writeToJSON if an object is null without throwing a NPE by trying to turn it into a String.
                writeToJSON(account, item, "type", account.getAccountType() != null ? account.getAccountType().toString() : null);
                writeToJSON(account, item, "email", account.getEmail());
                writeToJSON(account, item, "password", account.getPassword());
                writeToJSON(account, item, "id", account.getId() != null ? account.getId().toString() : null);
                writeToJSON(account, item, "firstName", account.getFirstName());
                writeToJSON(account, item, "lastName", account.getLastName());
                writeToJSON(account, item, "muted", account.isMuted() ? "true" : "false");
                //Still waiting for Progress to be implemented.. We'll add it later when it is.
                writer.write(item.toJSONString() + System.lineSeparator());
                //We can turn this part into a comment later.
                System.out.println("dataWriter(saveAccounts): Successfully saved account " + account.getUsername() + "!");
                System.out.println("dataWriter(saveAccounts): Contents: " + item.toJSONString());
            }
            writer.close(); //We might want to switch to BufferedWriter? It's fine for now though.
        } catch(IOException e){
            e.printStackTrace();
            return false; //Something definitely went wrong here!
        }
        return accounts != null; //If there's nothing saved, something probably went wrong
    }
    public static boolean saveProblems(ArrayList<Problem> problems) {
        try(FileWriter writer = new FileWriter("problemsite\\fwproblemsolversite\\src\\main\\resources\\jsonSamples\\problems.json")){
            for(Problem problem : problems){
                JSONObject item = new JSONObject();
                writeToJSON(problem, item, "title", problem.getTitle());
                writeToJSON(problem, item, "id", problem.getID() != null ? problem.getID().toString() : null);
                writeToJSON(problem, item, "description", problem.getDescription());
                writeToJSON(problem, item, "difficulty", problem.getDifficulty() != null ? problem.getDifficulty().toString() : null);
                writeToJSON(problem, item, "language", problem.getLanguage() != null ? problem.getLanguage().toString() : null);
                writeToJSON(problem, item, "notes", problem.getNotes() != null ? problem.getNotes() : null);
                writeToJSON(problem, item, "examples", problem.getExamples() != null ? problem.getExamples() : null);
                writeToJSON(problem, item, "submissions", problem.getSubmissions() != null ? problem.getSubmissions().toString() : null);
                writeToJSON(problem, item, "tags", problem.getTags() != null ? problem.getTags() : null);
                //Noteworthy exception to our formula from above for timer since it's a Double and not an enum or array of some kind.
                //We still want to save it as a String though to prevent errors. This requires String.valueOf(...) instead of (...).toString().
                writeToJSON(problem, item, "timer", problem.getTimer() != null ? String.valueOf(problem.getTimer().toDouble()) : null);
                writeToJSON(problem, item, "type", problem.getType() != null ? problem.getType().toString() : null);
                writeToJSON(problem, item, "constraints", problem.getConstraints() != null ? problem.getConstraints() : null);
                writeToJSON(problem, item, "answer", problem.getAnswer());
                writer.write(item.toJSONString() + System.lineSeparator());
                System.out.println("dataWriter(saveProblems): Successfully saved problem " + problem.getID() + "!");
                System.out.println("dataWriter(saveProblems): Contents: " + item.toJSONString());
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return problems != null;
    }
    public static boolean saveReports(ArrayList<Report> reports) {
        try(FileWriter writer = new FileWriter("problemsite\\fwproblemsolversite\\src\\main\\resources\\jsonSamples\\reports.json")){
            for(Report report : reports){
                JSONObject item = new JSONObject();
                writeToJSON(report, item, "id", report.getID() != null ? report.getID().toString() : null);
                writeToJSON(report, item, "reason", report.getReason());
                writeToJSON(report, item, "accused", report.getAccused());
                writeToJSON(report, item, "sender", report.getSender());
                writer.write(item.toJSONString() + System.lineSeparator());
                System.out.println("dataWriter(saveReports): Successfully saved report " + report.getID() + "!");
                System.out.println("dataWriter(saveReports): Contents: " + item.toJSONString());
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return reports != null;
    }
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
        Problem newProblem = new Problem("Test", UUID.randomUUID(), "This is a test problem.", constraints, Language.CPP, examples, notes, ProblemType.ARRAY, tags, timer.toDouble(), "AAA", Difficulty.MEDIUM, comments, submissions);
        problems.add(newProblem);
        saveAccounts(accounts);
        saveProblems(problems);
        saveReports(reports);
    }
}
