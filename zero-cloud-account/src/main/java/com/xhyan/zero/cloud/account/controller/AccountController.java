package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户的REST服务
 *
 * @author xhyan
 */
@RestController
@Api(value = "/group", description = "账户相关的服务")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping(value = "/account", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "sign up account", httpMethod = "POST", value = "注册账户")
    @ApiResponses(value = {
        @ApiResponse(code = 601, message = "参数错误"),
        @ApiResponse(code = 701, message = "注册失败")
    })
    public void signUp(
        @ApiParam(required = true, value = "account data") @RequestBody AccountDTO account) {
        accountService.signUp(account);
    }

    @ApiOperation(notes = "query one account", httpMethod = "GET", value = "查询单个账户")
    @GetMapping(value = "/account/{accountId}")
    public AccountDTO queryOne(
        @ApiParam(required = true, value = "账户id") @PathVariable Long accountId) {
        return accountService.findOne(accountId);
    }

}
