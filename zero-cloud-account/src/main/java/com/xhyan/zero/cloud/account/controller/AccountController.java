package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.cloud.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户的REST服务
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void save(Account account) {
        accountService.save(account);
    }
}
