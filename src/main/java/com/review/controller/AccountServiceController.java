package com.review.controller;

import com.review.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountServiceController
{

    private final AccountService accountService;

    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String getIndexPage(Model model){

        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";
    }
}
