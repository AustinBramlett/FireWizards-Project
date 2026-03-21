package com.fwproblemsolversite.data;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.accounts.Account;

public class AccountData {

    private ArrayList<Account> accounts;
    private static AccountData accountData;

    private AccountData() {
        accounts = new ArrayList<>();
    }

    public static AccountData getInstance() {
        if (accountData == null) {
            accountData = new AccountData();
        }
        return accountData;
    }

    // Method to set the list of accounts 
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = (accounts == null) ? new ArrayList<>() : accounts;
    }

    // Method to get an account by its unique ID
    public Account getAccountById(UUID id) {
        if (id == null) return null;
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    // Method to get an account by its username
    public Account getAccountByUsername(String username) {
        if (username == null) return null;
        for (Account account : accounts) {
            if (account.getUsername() != null && account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    // Method to check if a username already exists in the system
    public boolean usernameExists(String username) {
        if (username == null) return false;
        for (Account account : accounts) {
            if (account.getUsername() != null && account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Method to add a new account to the system
    public void addAccount(Account account) {
        if (account == null) return;
        accounts.add(account);
    }

    // Method to remove an account from the system
    public void removeAccount(Account account) {
        if (account == null) return;
        accounts.remove(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Method to ban an account by removing it from the list of accounts
    public void ban(Account account) {
        if (account == null) return;
        accounts.remove(account);
    }

    // Method to unban an account by adding it back to the list of accounts
    public void unban(Account account) {
        if (account == null) return;
       
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public void mute(Account account) {
        if (account == null) return;
        account.setMuted(true);
    }

    public void unmute(Account account) {
        if (account == null) return;
        account.setMuted(false);
    }
}