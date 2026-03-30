package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.data.ProblemData;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.enums.Difficulty;
import com.fwproblemsolversite.enums.Language;
import com.fwproblemsolversite.enums.ProblemType;
import com.fwproblemsolversite.problems.Problem;


public class FacadeTest {
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    @Test
    public void testFacadeNotNull() {
        ProblemApplication app = new ProblemApplication();
        assertNotNull(app);
    }

    @Test
    public void testFacadeLogin() {
        ProblemApplication app = new ProblemApplication();
        AccountData accountData = AccountData.getInstance();
        //Note that the below account may already exist in the accounts.json file. If it does, the test will still pass as long as the credentials are correct.
        accountData.addAccount(new Account(UUID.randomUUID(), "John", "Miller", "jmiller", "jmiller@example.com", "pass123"));
        assertTrue(app.login("jmiller", "pass123").getUsername().equals("jmiller") ? true : false);
    }

    @Test
    public void testFacadeLoginFail() {
        ProblemApplication app = new ProblemApplication();
        assertNull(app.login("nonexistentuser", "wrongpassword"));
    }

    @Test
    public void testFacadeDupeAccount() {
        ProblemApplication app = new ProblemApplication();
        AccountData accountData = AccountData.getInstance();
        accountData.addAccount(new Account(UUID.randomUUID(), "Jane", "Doe", "jdoe", "jdoe@example.com", "pass123"));
        accountData.addAccount(new Account(UUID.randomUUID(), "Jane", "Doe", "jdoe", "jdoe@example.com", "pass456"));
        assertNull(app.login("jdoe", "pass456"));
        assertNotNull(app.login("jdoe", "pass123"));
    }

    @Test
    public void testFacadeLogout() {
        ProblemApplication app = new ProblemApplication();
        AccountData accountData = AccountData.getInstance();
        //Using account constructor instead of facade signup to evade any checks that may be present later so that I can create an otherwise impossible username for testing purposes.
        accountData.addAccount(new Account(UUID.randomUUID(), "Jason", "Smith", "I\'M NOT IN THE JSON FILE", "asmith@example.com", "pass123"));
        assertNotNull(app.login("I\'M NOT IN THE JSON FILE", "pass123"));
        app.logout();
        assertNull(app.getCurrentUser());
    }

    @Test
    public void testFacadeGetCurrentUser() {
        ProblemApplication app = new ProblemApplication();
        AccountData accountData = AccountData.getInstance();
        accountData.addAccount(new Account(UUID.randomUUID(), "Emily", "Johnson", "I\'M ALSO NOT IN THE JSON FILE", "ejohnson@example.com", "pass123"));
        assertNull(app.getCurrentUser());
        app.login("I\'M ALSO NOT IN THE JSON FILE", "pass123");
        assertNotNull(app.getCurrentUser());
        assertTrue(app.getCurrentUser().getUsername().equals("I\'M ALSO NOT IN THE JSON FILE"));
    }

    @Test
    public void testFacadeSignup() {
        ProblemApplication app = new ProblemApplication();
        assertTrue(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
        assertNotNull(app.login("I\'M NOT IN THE JSON FILE", "pass123"));
    }

    @Test
    public void testFacadeSignupDuplicateUsername() {
        ProblemApplication app = new ProblemApplication();
        assertTrue(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "another@example.com", "pass456", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupDuplicateEmail() {
        ProblemApplication app = new ProblemApplication();
        assertTrue(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
        assertFalse(app.createAccount("Test", "User", "ANOTHER_USER", "initef@example.com", "pass456", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupInvalidEmail() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "notAnEmail", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupShortPassword() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "w3ak", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNoNumberPassword() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "weakpassword", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupEmptyFirstName() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupEmptyLastName() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupEmptyUsername() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "", "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupEmptyEmail() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupEmptyPassword() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullFirstName() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount(null, "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullLastName() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", null, "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullUsername() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", null, "initef@example.com", "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullEmail() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", null, "pass123", AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullPassword() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", null, AccountType.STUDENT));
    }

    @Test
    public void testFacadeSignupNullAccountType() {
        ProblemApplication app = new ProblemApplication();
        assertFalse(app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", null));
    }

    @Test
    public void testFacadeSolveProblemWithoutLogin() {
        ProblemApplication app = new ProblemApplication();
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        assertFalse(app.solveProblem("Test Problem"));
    }

     @Test
     public void testFacadeSolveProblemWithLogin() {
         ProblemApplication app = new ProblemApplication();
         app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
         app.login("I\'M NOT IN THE JSON FILE", "pass123");
         ProblemData problemData = ProblemData.getInstance();
         problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
         assertTrue(app.solveProblem("Test Problem"));
     }

     @Test
     public void testFacadeSolveNonexistentProblem() {
         ProblemApplication app = new ProblemApplication();
         app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
         app.login("I\'M NOT IN THE JSON FILE", "pass123");
         assertFalse(app.solveProblem("Nonexistent Problem"));
     }

     @Test
     public void testFacadeSolveProblemWithNullTitle() {
         ProblemApplication app = new ProblemApplication();
         app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
         app.login("I\'M NOT IN THE JSON FILE", "pass123");
         assertFalse(app.solveProblem(null));
         //I did not expect this to pass when I wrote it, but it did. Turns out that equalsIgnoreCase can handle null inputs without throwing a null pointer exception.
     }
    @Test
    public void testFacadeCommentWithoutLogin() {
        ProblemApplication app = new ProblemApplication();
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        // Test comment without login
        assertFalse(app.addComment("Test Problem", "This comment should not be posted."));
    }
    @Test
    public void testFacadeCommentWhileMuted() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        // Test comment while muted
        app.getCurrentUser().setMuted(true);
        assertFalse(app.addComment("Test Problem", "This comment should not be posted."));
    }

    @Test
    public void testFacadeCommentWithLogin() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
         ));
        // Test comment with login
        assertTrue(app.addComment("Test Problem", "This comment should be posted."));
    }
    
    @Test
    public void testFacadeCommentOnNonexistentProblem() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test comment on nonexistent problem
        assertFalse(app.addComment("Nonexistent Problem", "This comment should not be posted."));
    }

    @Test
    public void testFacadeAddCommentWithNullProblemTitle() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test comment with null problem title
        assertFalse(app.addComment(null, "This comment shouldn't invoke a Null Pointer Exception."));
    }

    @Test
    public void testFacadeAddCommentWithNullComment() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        // Test comment with null comment
        assertFalse(app.addComment("Test Problem", null));
    }

    @Test
    public void testFacadeAddCommentWithEmptyComment() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        // Test comment with empty comment
        assertFalse(app.addComment("Test Problem", ""));
    }

    @Test
    public void testFacadeAddCommentWithWhitespaceComment() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        ProblemData problemData = ProblemData.getInstance();
        problemData.add(new Problem(
            "Test Problem",
            "This is a test problem.",
            new ArrayList<>(),
            Language.JAVA,
            new ArrayList<>(),
            new ArrayList<>(),
            ProblemType.ARRAY,
            new ArrayList<>(),
            5.0,
            new ArrayList<>(),
            Difficulty.EASY
        ));
        // Test comment with whitespace comment
        assertFalse(app.addComment("Test Problem", "   ")); //Thought I was being clever here but turns out it still works. Oh well.
    }

    @Test
    public void testFacadeAddProblem() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with valid data
        assertTrue(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemAsStudent() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.STUDENT);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with valid data but while logged in as a student
        assertFalse(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemWithNullTitle() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with null title
        assertFalse(app.addProblem(null, "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }
    
    @Test
    public void testFacadeAddProblemWithEmptyTitle() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with empty title
        assertFalse(app.addProblem("", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }
    
    @Test
    public void testFacadeAddProblemWithNullDescription() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with null description
        assertFalse(app.addProblem("Test Problem", null, new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemWithNullLanguage() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with null language
        assertFalse(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemWithNullProblemType() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with null problem type
        assertFalse(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), null, new ArrayList<>(), 5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemWithNegativeTimer() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with negative timer
        assertFalse(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), -5.0, new ArrayList<>(), Difficulty.EASY));
    }

    @Test
    public void testFacadeAddProblemWithNullDifficulty() {
        ProblemApplication app = new ProblemApplication();
        app.createAccount("Test", "User", "I\'M NOT IN THE JSON FILE", "initef@example.com", "pass123", AccountType.CONTRIBUTOR);
        app.login("I\'M NOT IN THE JSON FILE", "pass123");
        // Test add problem with null difficulty
        assertFalse(app.addProblem("Test Problem", "This is a test problem.", new ArrayList<>(), Language.JAVA, new ArrayList<>(), new ArrayList<>(), ProblemType.ARRAY, new ArrayList<>(), 5.0, new ArrayList<>(), null));
    }
}