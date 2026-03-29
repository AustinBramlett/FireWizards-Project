package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.io.dataLoader;
import com.fwproblemsolversite.io.dataWriter;

public class DataWriterTest {

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // test saving accounts to the JSON file
    @Test
    public void testSaveAccountsValid() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        boolean result = dataWriter.saveAccounts(accounts);

        assertTrue(result);
    }

    // test saving null list of accounts should fail
    @Test
    public void testSaveAccountsNull() {
        boolean result = dataWriter.saveAccounts(null);

        assertFalse(result);
    }

    // test saving empty list of accounts should succeed
    @Test
    public void testSaveAccountsEmpty() {
        ArrayList<Account> accounts = new ArrayList<>();

        boolean result = dataWriter.saveAccounts(accounts);

        assertTrue(result); 
    }

    // test to see if the saved data can be reloaded and matches the original data
    @Test
    public void testSaveAndReload() {
        ArrayList<Account> before = dataLoader.LoadAccounts();

        dataWriter.saveAccounts(before);

        ArrayList<Account> after = dataLoader.LoadAccounts();

        assertEquals(before.size(), after.size());
    }

    // test to check that the saved data contains the expected information after saving
    @Test
    public void testNoNullAccountsAfterSave() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        dataWriter.saveAccounts(accounts);

        ArrayList<Account> after = dataLoader.LoadAccounts();

        for (Account acc : after) {
            assertNotNull(acc);
        }
    }
}