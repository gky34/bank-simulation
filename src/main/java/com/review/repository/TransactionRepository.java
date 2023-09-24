package com.review.repository;

import com.review.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRepository
{
    public static List<Transaction> transactionsList = new ArrayList<>();

    public Transaction save(Transaction transaction)
    {
        transactionsList.add(transaction);
        return transaction;
    }
}
