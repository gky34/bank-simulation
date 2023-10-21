package com.review.service;

import com.review.model.Account;
import com.review.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

    List<Transaction> findAllTransaction();

    List<Transaction> last10Transactions();

    List<Transaction> findTransactionListByID(UUID id);
}