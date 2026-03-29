package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.enums.AccountType;
import com.fwproblemsolversite.io.dataLoader;

public class AccountTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // checks the default values when a new account is created
    @Test
    public void testDefaultAccount() {
        Account account = new Account();

        assertNotNull(account.getId());              // cant be null
        assertEquals("", account.getUsername());     // username starts empty
        assertEquals(AccountType.STUDENT, account.getAccountType()); // default type
        assertFalse(account.isMuted());              // should not be muted by default
    }

    // can mute an account and check that it is muted
    @Test
    public void testSetMuted() {
        Account account = new Account();

        account.setMuted(true);

        assertTrue(account.isMuted()); // should now be muted
    }

    // checks that accounts load from the JSON file correctly 
    @Test
    public void testLoadAccountsFromJson() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertNotNull(accounts);         // cant be null
        assertTrue(accounts.size() > 0); // should have data
    }

    // checks first account matches the expected JSON data
    @Test
    public void testFirstAccountFromJson() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        Account first = accounts.get(0);

        assertEquals("jmiller", first.getUsername()); // needs username
        assertEquals("Jordan", first.getFirstName()); // needs first name
    }

    // checks that an admin account loads right from the JSON file
    @Test
    public void testAdminAccountFromJson() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        Account admin = accounts.get(1);

        assertEquals("jrichardson", admin.getUsername()); // admin username
    }
}