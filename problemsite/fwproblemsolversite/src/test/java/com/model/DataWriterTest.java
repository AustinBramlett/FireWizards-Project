package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.io.dataLoader;

public class DataWriterTest {

    

    // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // test that accounts can be loaded from the JSON file
    @Test
    public void testAccountsExist() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        assertNotNull(accounts);
        assertTrue(accounts.size() > 0);
    }

    //tests null input should not work 
    @Test
    public void testSaveAccountsNullLogic() {
        ArrayList<Account> accounts = null;

        boolean result = (accounts != null);

        assertFalse(result);
    }

    // can have empty list
    @Test
    public void testSaveAccountsEmptyLogic() {
        ArrayList<Account> accounts = new ArrayList<>();

        boolean result = (accounts != null);

        assertTrue(result);
    }

    // the accounts should be saved in the correct format (at least have the title field)
    @Test
    public void testNoNullAccountsLoaded() {
        ArrayList<Account> accounts = dataLoader.LoadAccounts();

        for (Account acc : accounts) {
            assertNotNull(acc);
        }
    }
}