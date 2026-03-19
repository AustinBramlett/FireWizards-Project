package com.fwproblemsolversite.drivers;

import java.util.ArrayList;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.enums.AccountType;

import org.json.simple.*;
import java.io.FileWriter;
import java.io.IOException;

//Note everything here saves to a singular line per object.
public class dataWriter {
    public static boolean saveAccounts(ArrayList<Account> accounts) {
        try(FileWriter writer = new FileWriter("problemsite\\fwproblemsolversite\\src\\main\\resources\\jsonSamples\\accounts.json")){
            for(Account account : accounts){
                JSONObject item = new JSONObject();
                if(account.getUsername() == null) {
                    System.out.println("Account has no username, skipping to prevent further error!");
                    continue; // Skip this account since username is essential
                }
                item.put("username", account.getUsername());
                if(account.getAccountType() == null) {
                    System.out.println("Account " + account.getUsername() + " has no account type, skipping to prevent further error!");
                    continue; // Skip this account since type is essential
                }
                switch(account.getAccountType()) {
                    case AccountType.ADMIN:
                        item.put("type", "ADMIN");
                        break;
                    case AccountType.STUDENT:
                        item.put("type", "STUDENT");
                        break;
                    case AccountType.CONTRIBUTOR:
                        item.put("type", "CONTRIBUTOR");
                        break;
                    default:
                        System.out.println("Invalid account type detected for " + account.getUsername() + "!");
                        continue; // Skip this account since type is essential
                }
                if(account.getEmail() == null) {
                    System.out.println("Account " + account.getUsername() + " has no email specified!");
                } else {
                    item.put("email", account.getEmail());
                }
                if(account.getPassword() == null) {
                    System.out.println("Account " + account.getUsername() + " has no password specified!");
                } else {
                    item.put("password", account.getPassword());
                }
                 if(account.getId() == null) {
                    System.out.println("Account " + account.getUsername() + " has no ID!");
                } else {
                    item.put("id", account.getId().toString());
                }
                if(account.getFirstName() == null) {
                    System.out.println("Account " + account.getUsername() + " has no first name specified!");
                } else {
                    item.put("firstName", account.getFirstName());
                }
                if(account.getLastName() == null) {
                    System.out.println("Account " + account.getUsername() + " has no last name specified!");
                } else {
                    item.put("lastName", account.getLastName());
                }
                // item.put("progress", account.getProgress()); does not exist since Progress isn't fully implemented yet. We can add it later when it is.
                item.put("muted", account.isMuted()); //I suggest moving this above type in the JSON.
                writer.write(item.toJSONString() + System.lineSeparator());
                //We can turn this part into a comment later.
                System.out.println("Successfully saved account " + account.getUsername() + "!");
                System.out.println("Contents: " + item.toJSONString());
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
                if(problem.getTitle() == null) {
                    System.out.println("Problem has no title, skipping to prevent further error!");
                    continue; // Skip this problem since title is essential
                }
                item.put("title", problem.getTitle());
                if(problem.getID() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no ID!");
                } else {
                    item.put("id", problem.getID().toString());
                }
                if(problem.getDescription() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no description!");
                } else {
                    item.put("description", problem.getDescription());
                }
                if(problem.getDifficulty() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no difficulty!");
                } else {
                    item.put("difficulty", problem.getDifficulty().toString());
                }
                if(problem.getType() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no type!");
                } else {
                    item.put("accountType", problem.getType().toString());
                }
                if(problem.getTags() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no tags!");
                } else {
                    item.put("tags", problem.getTags());
                }
                 if(problem.getConstraints() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no constraints!");
                } else {
                    item.put("constraints", problem.getConstraints());
                }
                if(problem.getLanguage() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no language specified!");
                } else {
                    item.put("language", problem.getLanguage().toString());
                }
                if (problem.getExamples() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no examples!");
                } else {
                    item.put("examples", problem.getExamples());
                }
                 if(problem.getNotes() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no notes!");
                } else {
                    item.put("notes", problem.getNotes());
                }
                if(problem.getTimer() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no timer specified!");
                } else {
                    item.put("timer", problem.getTimeLimit());
                }
                if(problem.getComments() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no comments array!");
                } else {
                    item.put("comments", problem.getComments());
                }
                if(problem.getSubmissions() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no submissions array!");
                } else {
                    item.put("submissions", problem.getSubmissions());
                }
                 if(problem.getAnswer() == null) {
                    System.out.println("Problem " + problem.getTitle() + " has no answer specified!");
                } else {
                    item.put("answer", problem.getAnswer());
                }
                writer.write(item.toJSONString() + System.lineSeparator());
                System.out.println("Successfully saved problem " + problem.getID() + "!");
                System.out.println("Contents: " + item.toJSONString());
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
                if(report.getID() == null) {
                    System.out.println("Report has no ID, skipping to prevent further error!");
                    continue; // Skip this report since ID is essential
                }
                 if(report.getAccused() == null) {
                    System.out.println("Report " + report.getID() + " has no accused specified!");
                } else {
                    item.put("accused", report.getAccused());
                }
                 if(report.getReason() == null) {
                    System.out.println("Report " + report.getID() + " has no reason specified!");
                } else {
                    item.put("reason", report.getReason());
                }
                 if(report.getSender() == null) {
                    System.out.println("Report " + report.getID() + " has no sender specified!");
                } else {
                    item.put("sender", report.getSender());
                }
                writer.write(item.toJSONString() + System.lineSeparator());
                System.out.println("Successfully saved report " + report.getID() + "!");
                System.out.println("Contents: " + item.toJSONString());
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
        saveAccounts(accounts);
        saveProblems(problems);
        saveReports(reports);
    }
}
