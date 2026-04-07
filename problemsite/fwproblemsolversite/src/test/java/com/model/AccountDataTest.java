package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.data.AccountData;

public class AccountDataTest {
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    @Test
    public void testSingleton() {
        AccountData instance1 = AccountData.getInstance();
        AccountData instance2 = AccountData.getInstance();
        assertFalse(instance1 != instance2); // Both instances should be the same
    }
    @Test
    public void testGetAccountById() {
        AccountData accountData = AccountData.getInstance();
        Account account = new Account();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        accountData.getAccounts().add(account); // Add a new account

        Account retrieved = accountData.getAccountById(account.getId());
        assertNotNull(retrieved);
        assertEquals(account.getId(), retrieved.getId());
    }

    @Test
    public void testGetAccountByIdNotFound() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        Account retrieved = accountData.getAccountById(java.util.UUID.randomUUID());
        assertNull(retrieved);
    }

    @Test
    public void testGetAccountByIdNull() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        Account retrieved = accountData.getAccountById(null);
        assertNull(retrieved);
    }

    @Test
    public void testGetAccountByUsername() {
        AccountData accountData = AccountData.getInstance();
        Account account = new Account();
        account.setUsername("testuser");
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        accountData.getAccounts().add(account); // Add a new account

        Account retrieved = accountData.getAccountByUsername("testuser");
        assertNotNull(retrieved);
        assertEquals("testuser", retrieved.getUsername());
    }
    
    @Test
    public void testGetAccountByUsernameNotFound() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        Account retrieved = accountData.getAccountByUsername("nonexistent");
        assertNull(retrieved);
    }

    @Test
    public void testGetAccountByUsernameNull() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        Account retrieved = accountData.getAccountByUsername(null);
        assertNull(retrieved);
    }

    @Test
    public void testSetAccountsNull() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(null);
        assertNotNull(accountData.getAccounts());
        assertTrue(accountData.getAccounts().isEmpty());
    }

    @Test
    public void testSetAccounts() {
        AccountData accountData = AccountData.getInstance();
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accountData.setAccounts(accounts);
        assertEquals(1, accountData.getAccounts().size());
    }

    @Test
    public void testUsernameExists() {
        AccountData accountData = AccountData.getInstance();
        Account account = new Account();
        account.setUsername("existinguser");
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        accountData.getAccounts().add(account); // Add a new account

        assertTrue(accountData.usernameExists("existinguser"));
        assertFalse(accountData.usernameExists("nonexistent"));
    }

    @Test
    public void testUsernameExistsNull() {
        AccountData accountData = AccountData.getInstance();
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        assertFalse(accountData.usernameExists(null));
    }

    @Test
    public void testUsernameExistsWithNullUsernames() {
        AccountData accountData = AccountData.getInstance();
        Account account1 = new Account();
        account1.setUsername(null);
        Account account2 = new Account();
        account2.setUsername("validuser");
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        accountData.getAccounts().add(account1);
        accountData.getAccounts().add(account2);

        assertFalse(accountData.usernameExists(null));
        assertTrue(accountData.usernameExists("validuser"));
    }

    @Test
    public void testMuteAccount() {
        AccountData accountData = AccountData.getInstance();
        Account account = new Account();
        account.setUsername("muteduser");
        accountData.setAccounts(new ArrayList<>()); // Clear existing accounts
        accountData.addAccount(account); // Add a new account
        accountData.mute(account);
        assertTrue(account.isMuted()); //Ask the object if it is muted
    }

    @Test
    public void testUnmuteAccount() {
        AccountData accountData = AccountData.getInstance();
        Account account = new Account();
        account.setUsername("unmuteduser");
        accountData.setAccounts(new ArrayList<>());
        accountData.addAccount(account);
        accountData.mute(account);
        assertTrue(account.isMuted());
        accountData.unmute(account);
        assertFalse(account.isMuted());
    }

}
