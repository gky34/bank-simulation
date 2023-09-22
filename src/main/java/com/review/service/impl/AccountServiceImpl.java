package com.review.service.impl;

import com.review.enums.AccountType;
import com.review.model.Account;
import com.review.repository.AccountRepository;
import com.review.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {

        // 1- need to create Account object
        // 2- save into database(repository)
        // 3- return object created

        Account account = Account.builder()
                .id(UUID.randomUUID()).userId(userId)
                .accountType(accountType).creationDate(createDate).build();

        return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccount() {
        return null;
    }
}
