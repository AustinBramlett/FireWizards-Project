package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.io.dataLoader;

public class DataLoaderTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // checks accounts can load from the JSON file
    @Test
    public void testLoadAccountsNotNull() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertNotNull(accounts);
    }

    // checks to see if the accounts list is not empty after loading
    @Test
    public void testLoadAccountsNotEmpty() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertTrue(accounts.size() > 0);
    }

    // makes sure the first account loaded has the right username from the JSON file
    @Test
    public void testFirstAccountUsername() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertEquals("jmiller", accounts.get(0).getUsername());
    }

    // test to check if multiple accounts are loaded from the JSON file
    @Test
    public void testMultipleAccountsLoaded() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertTrue(accounts.size() >= 2);
    }

    // no account in the loaded list should be null
    @Test
    public void testNoNullAccounts() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        for (Account acc : accounts) {
            assertNotNull(acc);
        }
    }
}