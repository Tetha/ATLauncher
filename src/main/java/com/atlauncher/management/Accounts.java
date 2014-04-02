package com.atlauncher.management;

import com.atlauncher.type.Account;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Accounts{
    INSTANCE;

    private final List<Account> accounts = new LinkedList<Account>();
    private int current = 0;

    public void add(Account acc){
        this.accounts.add(acc);
    }

    public static List<Account> all(){
        return Collections.unmodifiableList(INSTANCE.accounts);
    }

    public static Account current(){
        return INSTANCE.accounts.get(INSTANCE.current);
    }

    public static String getSkinURL(String username){
        return "http://s3.amazonaws.com/MinecraftSkins/" + username + ".png";
    }
}