package com.model;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.ProblemApplication;
import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;
import com.fwproblemsolversite.enums.AccountType;


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
}
