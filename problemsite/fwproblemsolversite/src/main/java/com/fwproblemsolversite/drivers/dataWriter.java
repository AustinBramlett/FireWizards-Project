package com.fwproblemsolversite.drivers;

import java.util.ArrayList;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.accounts.Report;
import com.fwproblemsolversite.enums.AccountType;

import org.json.simple.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

//Note everything here saves to a singular line per object.
public class dataWriter {
    public static boolean saveAccounts(ArrayList<Account> accounts) {
        try(FileWriter writer = new FileWriter("json/accounts.json")){
            for(Account account : accounts){
                JSONObject item = new JSONObject();
                AccountType type = account.getAccountType();
                item.put("id", account.getID());
                item.put("firstName", account.getFirstName());
                item.put("lastName", account.getLastName());
                item.put("username", account.getUsername());
                item.put("email", account.getEmail());
                item.put("password", account.getPassword());
                item.put("type", type.toString());
                switch(type){
                    case AccountType.ADMIN:
                        //Nothing unique to admins in the json atm. Update later
                        break;
                    case AccountType.STUDENT:
                        //Same case for students
                        break;
                    case AccountType.CONTRIBUTOR:
                        //and same case for contributors
                        break;
                    default:
                        System.out.println("Invalid account type detected for " + account.getUsername() + "!");
                        break;
                }
                item.put("muted", account.getMuted()); //I suggest moving this above type in the JSON.
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
        try(FileWriter writer = new FileWriter("json/problems.json")){
            for(Problem problem : problems){
                JSONObject item = new JSONObject();
                item.put("id", problem.getID());
                item.put("title", problem.getTitle());
                item.put("description", problem.getDescription());
                item.put("difficulty", problem.getDifficulty().toString());
                item.put("constraints", problem.getConstraints());
                item.put("language", problem.getLanguage().toString());
                item.put("examples", problem.getExamples());
                item.put("notes", problem.getNotes());
                item.put("type", problem.getType().toString());
                item.put("tags", problem.getTags());
                item.put("timer", problem.getTimer());
                item.put("comments", problem.getComments());
                item.put("answer", problem.getAnswer());
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
        try(FileWriter writer = new FileWriter("json/reports.json")){
            for(Report report : reports){
                JSONObject item = new JSONObject();
                item.put("id", report.getID());
                item.put("accused", report.getAccused());
                item.put("reason", report.getReason());
                item.put("sender", report.getSender());
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
}
