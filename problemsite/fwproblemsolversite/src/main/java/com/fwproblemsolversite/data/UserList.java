package com.fwproblemsolversite.data;

import java.util.ArrayList;
import java.util.UUID;

import com.fwproblemsolversite.accounts.Account;

public class UserList {

    private ArrayList<Account> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public void setUsers(ArrayList<Account> users) {
        this.users = users;
    }

    public Account getUserByID(UUID id) {
        for (Account user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Account getUser(String username, String password) {
        for (Account user : users) {
            if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}