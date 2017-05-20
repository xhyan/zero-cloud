package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.cloud.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户服务
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    public void accountTest(Account account) {
        accountService.save(account);
    }
}
