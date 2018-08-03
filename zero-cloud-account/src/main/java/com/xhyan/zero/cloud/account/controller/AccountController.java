package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.client.WalletClient;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.dto.resp.AccountAllInfo;
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
@Api(value = "/account", description = "账户相关服务")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private WalletClient walletClient;

    @GetMapping(value = "/verification/code/{type}/{mobile}")
    @ApiOperation(notes = "sign up account", httpMethod = "GET", value = "发送短息验证码")
    public void sendAuthenticationCode( @ApiParam(required = true, value = "验证码类型:1 注册;2 登录") @PathVariable Integer type,
        @ApiParam(required = true, value = "手机号") @PathVariable String mobile) {
        smsService.sendVerificationCode(mobile, VerificationCodeTypeEnum.getByType(type));
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
    public AccountDTO queryOne(@PathVariable("accountId") Long accountId) {
        return accountService.findOne(accountId);
    }

    @GetMapping(value = "/account/all/{accountId}")
    @ApiOperation(notes = "query account and completed tasks", httpMethod = "GET", value = "查询账户信息及已完成任务列表")
    public AccountAllInfo queryAccountTask( @ApiParam(required = true, name = "账户id")  @PathVariable Long accountId) {
        AccountDTO account = accountService.findOne(accountId);
        List<TaskDTO> tasks = accountService.queryAccountTasks(accountId);
        if (account.getMining() == 1) {
            //查询账户钱包余额
            account.setBalance(walletClient.queryBalance(accountId));
        }
        return AccountAllInfo.builder().account(account).taskList(tasks).build();
    }

    /**
     * 账户完成任务
     */
    @PostMapping(value = "/task/complete")
    @ApiOperation(notes = "account complete task", httpMethod = "POST", value = "账户完成任务，增加对应能量值")
    public Integer complete( @ApiParam(required = true, name = "账户id")  @RequestParam("accountId") Long accountId,
        @ApiParam(required = true, name = "任务id")  @RequestParam("taskId") Long taskId) {
        return accountService.completeTask(accountId, taskId);
    }

    @ApiOperation(notes = "account certification", httpMethod = "POST", value = "账户实名认证")
    @GetMapping(value = "/certification/{accountId}")
    public void certification( @ApiParam(required = true, name = "账户id") @PathVariable Long accountId,
        @ApiParam(required = true, name = "姓名") @RequestParam(name = "name") String name,
        @ApiParam(required = true, name = "身份证号")  @RequestParam(name = "identityCard") String identityCard) {
        accountService.certification(accountId, name, identityCard);
    }


}
