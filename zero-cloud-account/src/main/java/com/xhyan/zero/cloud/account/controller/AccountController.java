package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.dto.resp.AccountTaskResp;
import com.xhyan.zero.cloud.account.enums.VerificationCodeTypeEnum;
import com.xhyan.zero.cloud.account.service.AccountService;
import com.xhyan.zero.cloud.account.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private SmsService smsService;

    @GetMapping(value = "/verification/code/{mobile}")
    @ApiOperation(notes = "sign up account", httpMethod = "GET", value = "注册账户")
    public void sendAuthenticationCode(
        @ApiParam(required = true, value = "手机号") @PathVariable String mobile) {
        smsService.sendVerificationCode(mobile, VerificationCodeTypeEnum.SIGN_UP);
    }

    @GetMapping(value = "/account")
    @ApiOperation(notes = "sign up account", httpMethod = "GET", value = "注册账户")
    @ApiResponses(value = {
        @ApiResponse(code = 50001, message = "短信验证码错误"),
        @ApiResponse(code = 701, message = "注册失败")
    })
    public void signUp(
        @ApiParam(required = true, value = "手机号") @RequestParam(name = "mobile") String mobile,
        @ApiParam(required = true, value = "短信验证码") @RequestParam(name = "verifyCode") String verifyCode) {
        accountService.signUp(mobile, verifyCode);
    }

    @GetMapping(value = "/account/{accountId}")
    @ApiOperation(notes = "query one account", httpMethod = "GET", value = "查询单个账户")
    public AccountDTO queryOne(
        @ApiParam(required = true, value = "账户id") @PathVariable Long accountId) {
        return accountService.findOne(accountId);
    }

    @GetMapping(value = "/account/{accountId}/tasks")
    @ApiOperation(notes = "query account and completed tasks", httpMethod = "GET", value = "查询账户信息及已完成任务列表")
    public AccountTaskResp queryAccountTask(@PathVariable Long accountId) {
        AccountDTO account = accountService.findOne(accountId);
        List<TaskDTO> tasks = accountService.queryAccountTasks(accountId);
        return AccountTaskResp.builder().account(account).taskList(tasks).build();
    }

    /**
     * 账户完成任务
     */
    @PostMapping(value = "/task/complete")
    @ApiOperation(notes = "account complete task", httpMethod = "GET", value = "账户完成任务，增加对应能量值")
    public Integer complete(@RequestParam("accountId") Long accountId,
        @RequestParam("taskId") Long taskId) {
        return accountService.completeTask(accountId, taskId);
    }

    @ApiOperation(notes = "account certification", httpMethod = "GET", value = "账户实名认证")
    @GetMapping(value = "/certification/{accountId}")
    public void certification(@PathVariable Long accountId,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "identityCard") String identityCard) {
        accountService.certification(accountId, name, identityCard);
    }


}
