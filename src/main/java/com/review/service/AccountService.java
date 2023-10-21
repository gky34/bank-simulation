package com.review.service;

import com.review.enums.AccountType;
import com.review.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType,Long userId);

    List<Account> listAllAccount();

    void deleteAccount(UUID id);

    void activateAccount(UUID id);

    Account retrieveById(UUID id);
}