package com.review.controller;

import com.review.enums.AccountType;
import com.review.model.Account;
import com.review.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class AccountServiceController {

    private final AccountService accountService;

    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String getIndexPage(Model model) {

        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model) {

        //we need to provide empty account object
        model.addAttribute("account", Account.builder().build());
        //we need to provide accountType enum info for filling the dropdown options
        model.addAttribute("accountTypes",AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String createNewAccount(@ModelAttribute("account") Account account, Model model){

        System.out.println(account);
        accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id") UUID id){

        accountService.deleteAccount(id);

        return "redirect:/index";
    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") UUID id){

        accountService.activateAccount(id);

        return "redirect:/index";
    }
}
