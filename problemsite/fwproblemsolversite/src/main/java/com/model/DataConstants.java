package com.model;

public class DataConstants {
    protected static final String ACCOUNT_FILE_NAME = "problemsite\\fwproblemsolversite\\src\\main\\resources\\json\\accounts.json";
    protected static final String PROBLEM_FILE_NAME = "problemsite\\fwproblemsolversite\\src\\main\\resources\\json\\problems.json";
    protected static final String REPORT_FILE_NAME = "problemsite\\fwproblemsolversite\\src\\main\\resources\\json\\reports.json";
    //Since tests are in their own folder and junit takes directories with the resources folder (in the test folder) as the root, we shorten the directory.
    protected static final String ACCOUNT_TEMP_FILE_NAME = "json\\accounts.json";
    protected static final String PROBLEM_TEMP_FILE_NAME = "json\\problems.json";
    protected static final String REPORT_TEMP_FILE_NAME = "json\\reports.json";

    protected static final String ITEM_ID = "id";
    protected static final String ACCOUNT_USER_NAME = "username";
    protected static final String ACCOUNT_FIRST_NAME = "firstName";
    protected static final String ACCOUNT_LAST_NAME = "lastName";
    protected static final String ACCOUNT_MUTED = "muted";
    protected static final String ACCOUNT_TYPE = "accountType";
    protected static final String ACCOUNT_EMAIL = "email";
    protected static final String ACCOUNT_LAST_DATE = "lastDate";
    protected static final String ACCOUNT_PROGRESS = "progress";
    protected static final String ACCOUNT_PASSWORD = "password";

    protected static final String PROBLEM_TITLE = "title";
    protected static final String PROBLEM_DESCRIPTION = "description";
    protected static final String PROBLEM_DIFFICULTY = "difficulty";
    protected static final String PROBLEM_LANGUAGE = "language";
    protected static final String PROBLEM_NOTES = "notes";
    protected static final String PROBLEM_SUBMISSIONS = "submissions";
    protected static final String PROBLEM_TAGS = "tags";
    protected static final String PROBLEM_TIMER = "timer";
    protected static final String PROBLEM_TYPE = "type";
    protected static final String PROBLEM_CONSTRAINTS = "constraints";
    protected static final String PROBLEM_ANSWERS = "answers";
    protected static final String PROBLEM_COMMENTS = "comments";
    protected static final String PROBLEM_EXAMPLES = "examples";

    protected static final String COMMENT_SENDER = "sender";
    protected static final String COMMENT_SCORE = "score";
    protected static final String COMMENT_DATE = "date";
    protected static final String COMMENT_TEXT = "commentText";
    protected static final String COMMENT_REPLIES = "replies";

    protected static final String REPORT_SENDER = "sender";
    protected static final String REPORT_REASON = "reason";
    protected static final String REPORT_ACCUSED = "accused";  
    
    public static boolean isJUnitTest() {
        for(StackTraceElement element : Thread.currentThread().getStackTrace()){
            if(element.getClassName().startsWith("org.junit.")){
                return true;
            }
        }
        return false;
    }
}
