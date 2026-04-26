package com.fwproblemsolversite.io;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
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
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Comment;
import com.fwproblemsolversite.problems.Problem;
import com.fwproblemsolversite.problems.Submission;
import com.model.DataConstants;

/**
 * Handles loading data from JSON files into the system. This class will read
 * accounts, problems, and reports from JSON files. And then convert them.
 */
public class dataLoader extends DataConstants {

    private static JSONParser parser = new JSONParser();
    private static boolean debug = false;

    /**
     * Turns on dataLoader's debug mode. This will print out extra information
     * about the data loading process to help with debugging.
     */
    public static void debugMode() {
        debug = true;
    }

    /**
     * Prints the output to the console if debug mode is on.
     *
     * @param output The string to be printed to the console if debug mode is
     * on. Will leave a trace to dataLoader.
     */
    private static void debugOut(String output) {
        if (debug) {
            System.out.println("dataLoader: " + output);
        }
    }

    /**
     * Loads the reports from the JSON file.
     *
     * @return list of reports
     */
    public static ArrayList<Report> LoadReports() {
        ArrayList<Report> reports = new ArrayList<>();
        String fileName = REPORT_FILE_NAME;
        if (isJUnitTest()) {
            fileName = REPORT_TEMP_FILE_NAME;
        }
        try (FileReader reader = new FileReader(fileName)) {
            JSONArray jsonObject = (JSONArray) parser.parse(reader);
            for (Object obj : jsonObject) {
                JSONObject reportObj = (JSONObject) obj;
                // Parse the JSON and create Report instances
                String id = (String) reportObj.get(ITEM_ID);
                String reason = (String) reportObj.get(REPORT_REASON);
                String accused = (String) reportObj.get(REPORT_ACCUSED);
                String sender = (String) reportObj.get(REPORT_SENDER);
                Report report = new Report(reason, accused, sender, id);
                reports.add(report);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return reports; // Return the list of reports
    }

    /**
     * Loads all the problems from the JSON file.
     *
     * will parse through the details like difficulty, type, tags, comments, and
     * submissions.
     *
     * @return list of problems
     */
    public static ArrayList<Problem> LoadProblems() {
        ArrayList<Problem> problems = new ArrayList<>();
        String fileName = PROBLEM_FILE_NAME;
        if (isJUnitTest()) {
            fileName = PROBLEM_TEMP_FILE_NAME;
        }
        try (FileReader reader = new FileReader(fileName)) {
            JSONArray jsonObject = (JSONArray) parser.parse(reader);
            for (Object obj : jsonObject) {
                JSONObject problemObj = (JSONObject) obj;
                // Parses the JSON and create Problem instances.
                UUID id = (String) problemObj.get(ITEM_ID) != null ? UUID.fromString((String) problemObj.get(ITEM_ID)) : UUID.randomUUID();
                String title = (String) problemObj.get(PROBLEM_TITLE);
                String description = (String) problemObj.get(PROBLEM_DESCRIPTION);
                String difficultyString = (String) problemObj.get(PROBLEM_DIFFICULTY);
                Difficulty difficulty;
                if (title == null) {
                    debugOut("Title not specified for a problem, skipping to prevent further error!");
                    continue; // Skip this problem since title is essential
                }
                if (difficultyString == null) {
                    debugOut("Difficulty not specified for problem " + title);
                    difficulty = null;
                } else {
                    switch (difficultyString) {
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
                            debugOut("Unknown difficulty: " + difficultyString);
                            difficulty = null;
                    }
                }
                String typeString = (String) problemObj.get(PROBLEM_TYPE);
                ProblemType type = null;
                if (typeString == null) {
                    debugOut("Problem type not specified for problem " + title);
                } else {
                    switch (typeString) {
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
                            debugOut("Unknown problem type: " + typeString);
                            type = null;
                    }
                }
                String languageString = (String) problemObj.get(PROBLEM_LANGUAGE);
                Language language = null;
                if (languageString == null) {
                    debugOut("Language not specified for problem " + title);
                } else {
                    switch (languageString) {
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
                            debugOut("Unknown language: " + languageString);
                    }
                }
                JSONArray tagsArray = (JSONArray) problemObj.get(PROBLEM_TAGS);
                JSONArray constraintsArray = (JSONArray) problemObj.get(PROBLEM_CONSTRAINTS);
                JSONArray examplesArray = (JSONArray) problemObj.get(PROBLEM_EXAMPLES);
                JSONArray answersArray = (JSONArray) problemObj.get(PROBLEM_ANSWERS);
                JSONArray notesArray = (JSONArray) problemObj.get(PROBLEM_NOTES);
                JSONArray commentsArray = (JSONArray) problemObj.get(PROBLEM_COMMENTS);
                JSONArray submissionsArray = (JSONArray) problemObj.get(PROBLEM_SUBMISSIONS);
                ArrayList<String> tags = new ArrayList<>();
                if (tagsArray != null) {
                    for (Object tag : tagsArray) {
                        tags.add((String) tag);
                    }
                } else {
                    debugOut("No tags specified for problem " + title);
                }
                ArrayList<String> constraints = new ArrayList<>();
                if (constraintsArray != null) {
                    for (Object constraint : constraintsArray) {
                        constraints.add((String) constraint);
                    }
                } else {
                    debugOut("No constraints specified for problem " + title);
                }
                ArrayList<ArrayList<String>> examples = new ArrayList<>();
                if (examplesArray != null) {
                    for (Object example : examplesArray) {
                        JSONArray exampleObj = (JSONArray) example;
                        examples.add((ArrayList<String>) example);
                    }
                } else {
                    debugOut("No examples specified for problem " + title);
                }
                ArrayList<ArrayList<String>> answers = new ArrayList<>();
                if (answersArray != null) {
                    for (Object anAnswer : answersArray) {
                        answers.add((ArrayList<String>) anAnswer);
                    }
                } else {
                    debugOut("No answers specified for problem " + title);
                }
                ArrayList<String> notes = new ArrayList<>();
                if (notesArray != null) {
                    for (Object note : notesArray) {
                        notes.add((String) note);
                    }
                } else {
                    debugOut("No notes specified for problem " + title);
                }
                ArrayList<Comment> comments = new ArrayList<>();
                if (commentsArray != null) {
                    for (Object comment : commentsArray) {
                        JSONObject commentObj = (JSONObject) comment;

                        Comment commentInstance = loadComment(commentObj, id);

                        comments.add(commentInstance);
                    }
                } else {
                    debugOut("No comments specified for problem " + title);
                }
                ArrayList<Submission> submissions = new ArrayList<>();
                if (submissionsArray != null) {
                    for (Object submission : submissionsArray) {
                        JSONArray submissionObj = (JSONArray) submission;
                        Submission newSubmission = new Submission((ArrayList<String>) submission);
                        submissions.add(newSubmission);
                    }
                } else {
                    debugOut("No submissions array specified for problem " + title);
                }
                String timerVal = (String) problemObj.get(PROBLEM_TIMER);
                String code = (String) problemObj.get(PROBLEM_CODE);
                double timer = Double.parseDouble(timerVal);
                Problem problem = new Problem(title, id, description, constraints, language, examples, notes,
                        type, tags, timer, answers, difficulty, comments, submissions, code);
                //We're missing submissions and comments at the moment.
                problems.add(problem);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return problems; // Return the list of problems
    }

    /**
     * Loads all accounts from the JSON file. Creates different account types
     * based on the stored account type.
     *
     * @return list of accounts
     */
    public static ArrayList<Account> LoadAccounts() {
        String fileName = ACCOUNT_FILE_NAME;
        if (isJUnitTest()) {
            fileName = ACCOUNT_TEMP_FILE_NAME;
        }
        ArrayList<Account> accounts = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            JSONArray jsonObj = (JSONArray) parser.parse(reader);
            // Parse the JSON and create the proper Account instances based on the account type
            for (Object obj : jsonObj) {
                JSONObject accountObj = (JSONObject) obj;
                String accountType = (String) accountObj.get(ACCOUNT_TYPE);
                //Switch to determine which type of account to create
                Account accountInstance;
                //Every account has the follolwing:
                UUID id = UUID.fromString((String) accountObj.get(ITEM_ID));
                String firstName = (String) accountObj.get(ACCOUNT_FIRST_NAME);
                String lastName = (String) accountObj.get(ACCOUNT_LAST_NAME);
                String username = (String) accountObj.get(ACCOUNT_USER_NAME);
                String email = (String) accountObj.get(ACCOUNT_EMAIL);
                String password = (String) accountObj.get(ACCOUNT_PASSWORD);
                if (accountType == null) {
                    debugOut("Type not specified for this account, skipping to prevent further error!");
                } else {

                    ArrayList<Integer> progressData = new ArrayList<>();
                    JSONArray progressArray = (JSONArray) accountObj.get(ACCOUNT_PROGRESS);

                    if (progressArray != null) {
                        for (Object val : progressArray) {
                            progressData.add(((Long) val).intValue());
                        }
                    }

                    String lastDate = (String) accountObj.get(ACCOUNT_LAST_DATE);

                    com.fwproblemsolversite.data.Progress progress
                            = new com.fwproblemsolversite.data.Progress(progressData, lastDate);

                    switch (accountType) {
                        case "STUDENT":
                            accountInstance = new Student(id, firstName, lastName, username, email, password);
                            break;
                        case "ADMIN":
                            //logs are now stored in the json file, so we can load them when we create the admin account instance
                            ArrayList<UUID> adminBanLog = new ArrayList<>();
                            JSONArray banLogArray = (JSONArray) accountObj.get(ADMINISTRATOR_BAN_LOG);
                            if (banLogArray != null) {
                                for (Object ban : banLogArray) {
                                    adminBanLog.add(UUID.fromString((String) ban));
                                }
                            }
                            ArrayList<UUID> adminMuteLog = new ArrayList<>();
                            JSONArray muteLogArray = (JSONArray) accountObj.get(ADMINISTRATOR_MUTE_LOG);
                            if (muteLogArray != null) {
                                for (Object mute : muteLogArray) {
                                    adminMuteLog.add(UUID.fromString((String) mute));
                                }
                            }
                            ArrayList<UUID> adminTermLog = new ArrayList<>();
                            JSONArray termLogArray = (JSONArray) accountObj.get(ADMINISTRATOR_TERM_LOG);
                            if (termLogArray != null) {
                                for (Object term : termLogArray) {
                                    adminTermLog.add(UUID.fromString((String) term));
                                }
                            }
                            ArrayList<LocalDate> adminBanDates = new ArrayList<>();
                            JSONArray banDatesArray = (JSONArray) accountObj.get(ADMINISTRATOR_BAN_END_DATES);
                            if (banDatesArray != null) {
                                for (Object banDate : banDatesArray) {
                                    adminBanDates.add(LocalDate.parse((String) banDate));
                                }
                            }
                            ArrayList<LocalDate> adminMuteDates = new ArrayList<>();
                            JSONArray muteDatesArray = (JSONArray) accountObj.get(ADMINISTRATOR_MUTE_END_DATES);
                            if (muteDatesArray != null) {
                                for (Object muteDate : muteDatesArray) {
                                    adminMuteDates.add(LocalDate.parse((String) muteDate));
                                }
                            }
                            accountInstance = new Administrator(id, firstName, lastName, username, email, password, adminBanLog, adminMuteLog, adminTermLog, adminBanDates, adminMuteDates);
                            break;
                        case "CONTRIBUTOR":
                            ArrayList<UUID> questionsMade = new ArrayList<>();
                            accountInstance = new Contributor(id, firstName, lastName, username, email, password, questionsMade);
                            break;
                        default:
                            debugOut("Unknown account type: " + accountType);
                            accountInstance = null;
                    }
                    // Add the created account instance to the accounts list
                    if (accountInstance != null) {
                        accountInstance.setProgress(progress);
                        accounts.add(accountInstance);
                        //We're missing some info to create the account instance for anybody other than students. We'll have to fix that soon.
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return accounts; // Return the list of accounts
    }

    private static Comment loadComment(JSONObject commentObj, UUID problemID) {
        String commentText = (String) commentObj.get(COMMENT_TEXT);
        UUID sender = UUID.fromString((String) commentObj.get(COMMENT_SENDER));
        int score = ((Long) commentObj.get(COMMENT_SCORE)).intValue();
        String date = (String) commentObj.get(COMMENT_DATE);

        ArrayList<Comment> replies = new ArrayList<>();

        JSONArray repliesArray = (JSONArray) commentObj.get(COMMENT_REPLIES);

        if (repliesArray != null) {
            for (Object r : repliesArray) {
                JSONObject replyObj = (JSONObject) r;

                Comment reply = loadComment(replyObj, problemID); // 🔁 recursion
                replies.add(reply);
            }
        }

        return new Comment(sender, problemID, commentText, score, replies, date);
    }

    /**
     * Main method used for testing data loading.
     */
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
