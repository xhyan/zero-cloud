package com.xhyan.zero.cloud.account.controller;

import com.google.gson.Gson;
import com.xhyan.zero.cloud.account.converter.Converter;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.AccountDTO2;
import com.xhyan.zero.cloud.account.dto.resp.ZeroResponse;
import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.cloud.account.service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账户的REST服务
 */
@RestController
@Api(value = "/group", description = "账户相关的服务")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(notes = "create account", httpMethod = "POST", value = "添加账户")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "参数错误")
    })
    public boolean save(@ApiParam(required = true, value = "account data") @RequestBody AccountDTO account) {
        Account acc = Converter.convert(account, Account.class);
        acc.setStatus(1);
        return accountService.save(acc) > 0;
    }

    @ApiOperation(notes = "query one account", httpMethod = "GET", value = "查询单个账户")
    @GetMapping(value = "/account/{accountId}")
    public ZeroResponse<AccountDTO> queryOne(@ApiParam(required = true, value = "账户id") @PathVariable Long accountId) {
        Account account = accountService.selectByPrimaryKey(accountId);
        return ZeroResponse.success(Converter.convert(account, AccountDTO.class));
    }

    @ApiOperation(notes = "test", httpMethod = "POST", value = "测试接口", consumes = "multipart/form-data")
    @PostMapping(value = "/test", consumes = {"multipart/form-data"}, produces = {"multipart/form-data"})
    public String test(@RequestParam("file") MultipartFile file) {
        AccountDTO dto = new AccountDTO();
        dto.setEmail("222222");
        dto.setLoginName("xhyan");
        dto.setLoginPwd("1234");
        dto.setTradePwd("4321");
        dto.setStatus(1);
        Account account = Converter.convert(dto, Account.class);
        System.out.println(new Gson().toJson(account));
        return new Gson().toJson(account);
    }
}
