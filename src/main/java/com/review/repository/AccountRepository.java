package com.review.repository;

import com.review.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository
{
    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account){

        accountList.add(account);

        return account;
    }
}
