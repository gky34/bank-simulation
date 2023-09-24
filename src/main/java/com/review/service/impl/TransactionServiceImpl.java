package com.review.service.impl;

import com.review.enums.AccountType;
import com.review.exception.AccountOwnerShipException;
import com.review.exception.BadRequestException;
import com.review.exception.BalanceNotSuffincientException;
import com.review.model.Account;
import com.review.model.Transaction;
import com.review.repository.AccountRepository;
import com.review.repository.TransactionRepository;
import com.review.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {

        /**
         - if sender or receiver is null?
         - if sender and receiver is the same account?
         - if sender has enough balance to make transfer
         - if both accounts are checking, if not, one of them saving, it need to be same userID
         */

        validateAccount(sender, receiver);
        checkAccountOwnerShip(sender,receiver);
        executeBalanceAndUpdateIfRequire(amount,sender,receiver);

        /*
            after all validations are completed, and money is transferred,
            we need to create Transactions object and save return
         */

        Transaction transaction = Transaction.builder().amount(amount)
                .sender(sender.getId()).receiver(receiver.getId()).createDate(creationDate).message(message).build();


        // save into the db and return it
        return transactionRepository.save(transaction);
    }

    private void executeBalanceAndUpdateIfRequire(BigDecimal amount, Account sender, Account receiver)
    {
        if (checkSenderBalance(sender,amount))
        {
            // update sender and receiver balance
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else {
            throw new BalanceNotSuffincientException("Balance is not enough for this transfer");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {

        // verify sender has enough balance to send
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;

    }

    private void checkAccountOwnerShip(Account sender, Account receiver) {
        /*
            write an if statement that checks if one the account is saving
            and user of sender or receiver is not the same, throw AccountOwnerShipException
         */
        if (sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING)
        && !sender.getUserId().equals(receiver.getUserId()))
        {
            throw new AccountOwnerShipException("If one of the account is saving, user must be the same for sender or receiver");
        }
    }


    private void validateAccount(Account sender, Account receiver) {

        /**
         - if any og the account null
         -  if account ids are the same(same account)
         -  if the account exist in the database
         */

        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender Account needs to be different than receiver account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());


    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);

    }


    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
