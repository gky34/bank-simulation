package com.review.controller;


import com.review.model.Account;
import com.review.model.Transaction;
import com.review.service.AccountService;
import com.review.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model){

        //what we need to provide to make transfer happen
        //we need to provide empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());
        //we need to provide list of all accounts
        model.addAttribute("accounts",accountService.listAllAccount());
        //we need list of last 10 transactions to fill the table(business logic is missing)
        model.addAttribute("lastTransactions",transactionService.last10Transactions());
        return "transaction/make-transfer";
    }

    //write a post method that takes transaction object from the UI
    //complete the transfer and return the same page

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("accounts",accountService.listAllAccount());
            model.addAttribute("lastTransactions",transactionService.last10Transactions());
            return "transaction/make-transfer";
        }

        Account sender = accountService.retrieveById(transaction.getSender());
        Account receiver = accountService.retrieveById(transaction.getReceiver());

        return "redirect:/make-transfer";
    }


    //write a method, that get the account id from index.html and print it on the console
    //(work on index.html and here)
    //transaction/{id}

    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id")UUID id, Model model){

        System.out.println(id);

        // get the list of transaction based on id and returns as a model attribute
        // complete the method(service and repository)
        // findTransactionListByID

        model.addAttribute("transactions", transactionService.findTransactionListByID(id));

        return "/transaction/transaction";
    }
}