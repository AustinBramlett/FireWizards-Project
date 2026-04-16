package com.fwproblemsolversite.data;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.accounts.Account;
import com.fwproblemsolversite.accounts.Contributor;
/**
 * Manages all the account data in the system.
 * 
 * It provides methods for access, adding, removing, and modifying different accounts.
 */
public class AccountData {

    private ArrayList<Account> accounts;
    private static AccountData accountData;
    /**
     * Private constructor to prevent external instantiation.
     * Initializes the account list.
     */
    private AccountData() {
        accounts = new ArrayList<>();
    }
    /**
     * Returns the single instance of AccountData.
     * 
     * @return The AccountData instance.
     */
    public static AccountData getInstance() {
        if (accountData == null) {
            accountData = new AccountData();
        }
        return accountData;
    }
    /**
     * Sets the list of accounts .
     * 
     * @param accounts The list of accounts to store
     */
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = (accounts == null) ? new ArrayList<>() : accounts;
    }

    /**
     * Retrieves an account by its ID.
     * 
     * @param id The ID of the account
     * @return The Account that matches the ID, null if not found
     */
    public Account getAccountById(UUID id) {
        if (id == null) return null;
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    /**
     *  Retrieves the author of a problem by the problem's ID.
     *
     *  @param problemID The ID of the problem
     *  @return The Account that is the author of the problem, null if not found
     */
    public Account getAuthorById(UUID problemID) {
        if (problemID == null) return null;
        for (Account account : accounts) {
            if (!(account instanceof Contributor)) continue;
            Contributor contributor = (Contributor) account;
            if (contributor.checkAuthor(problemID)) {
                return contributor;
            }
        }
        return null;
    }

    /**
     * Retrieves an account by its username.
     * 
     * @param username The username of the account
     * @return The Account that matches the username, null if not found
     */
    public Account getAccountByUsername(String username) {
        if (username == null) return null;
        for (Account account : accounts) {
            if (account.getUsername() != null && account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Checks if a username already exists in the system.
     * 
     * @param username The username to check
     * @return true if the username exists, if not false.
     */
    public boolean usernameExists(String username) {
        if (username == null) return false;
        for (Account account : accounts) {
            if (account.getUsername() != null && account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new account to the system.
     * 
     * @param account The Account to be added.
     */
    public void addAccount(Account account) {
        if (account == null) return;
        accounts.add(account);
    }

    /**
     * Removes an account from the system.
     * 
     * @param account The Account to be removed.
     */
    public void removeAccount(Account account) {
        if (account == null) return;
        accounts.remove(account);
    }
    /**
     * Returns the list of all accounts.
     * 
     * @return The list of accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

   /**
    * Bans an account by removing it from the list of accounts.
     * 
     * @param account The Account to be banned.
    */
    public void ban(Account account) {
        if (account == null) return;
        accounts.remove(account);
    }

    /**
     * Unbans an accont by adding it back to the list of accounts.
      * 
      * @param account The Account to be unbanned.
     */
    public void unban(Account account) {
        if (account == null) return;
       
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }
    /**
     * Mutes an account by setting its muted status to true.
      * 
      * @param account The Account to be muted.
     */
    public void mute(Account account) {
        if (account == null) return;
        account.setMuted(true);
    }
    /**
     * Unmutes an account, allowing it to interact again.
     * 
     * @param account The Account to be unmuted.
     */
    public void unmute(Account account) {
        if (account == null) return;
        account.setMuted(false);
    }
}