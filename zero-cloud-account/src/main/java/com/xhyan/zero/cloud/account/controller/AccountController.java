package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.components.client.WalletClient;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.dto.req.AccountQueryReq;
import com.xhyan.zero.cloud.account.dto.resp.AccountAllInfo;
import com.xhyan.zero.cloud.account.enums.VerificationCodeTypeEnum;
import com.xhyan.zero.cloud.account.service.AccountService;
import com.xhyan.zero.cloud.account.service.SmsService;
import com.xhyan.zero.cloud.common.dto.ZeroPageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账户的REST服务
 *
 * @author xhyan
 */
@Api(value = "/account", description = "账户相关服务")
@CrossOrigin
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
    public void sendAuthenticationCode(@ApiParam(required = true, value = "验证码类型:1 注册;2 登录") @PathVariable Integer type,
                                       @ApiParam(required = true, value = "手机号") @PathVariable String mobile) {
        smsService.sendVerificationCode(mobile, VerificationCodeTypeEnum.getByType(type));
    }

//    @PostMapping(value = "/accounts")
//    @ApiOperation(notes = "sign up account", httpMethod = "POST", value = "注册账户")
//    @ApiResponses(value = {
//            @ApiResponse(code = 50001, message = "短信验证码错误"),
//            @ApiResponse(code = 701, message = "注册失败")
//    })
//    public void signUp(
//            @ApiParam(required = true, value = "手机号") @RequestParam(name = "mobile") String mobile,
//            @ApiParam(required = true, value = "短信验证码") @RequestParam(name = "verifyCode") String verifyCode) {
//        accountService.signUp(mobile, verifyCode);
//    }

    @PostMapping(value = "/accounts")
    @ApiOperation(notes = "sign up account", httpMethod = "POST", value = "注册账户")
    @ApiResponses(value = {
            @ApiResponse(code = 70001, message = "注册失败")
    })
    public void create(@ApiParam(required = true, value = "账户信息") @RequestBody AccountDTO dto) {
        accountService.create(dto);
    }

    @PutMapping(value = "/accounts")
    @ApiOperation(notes = "modify one account", httpMethod = "PUT", value = "修改账户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 70002, message = "修改账户信息失败")
    })
    public void modify(@ApiParam(required = true, value = "账户信息") @RequestBody AccountDTO dto) {
        accountService.modify(dto);
    }

    @GetMapping(value = "/accounts/{accountId}")
    @ApiOperation(notes = "query one account", httpMethod = "GET", value = "查询单个账户")
    public AccountDTO queryOne(@PathVariable("accountId") Long accountId) {
        return accountService.findOne(accountId);
    }

    @PostMapping(value = "/accounts/page")
    @ApiOperation(notes = "page query accounts", httpMethod = "POST", value = "分页查询账户信息")
    public ZeroPageInfo<AccountDTO> pageQuery(@RequestBody AccountQueryReq req) {
        return accountService.pageList(req);
    }

    @GetMapping(value = "/accounts/{accountId}/all_info")
    @ApiOperation(notes = "query account and completed tasks", httpMethod = "GET", value = "查询账户信息及已完成任务列表")
    public AccountAllInfo queryAccountTask(@ApiParam(required = true, name = "accountId", value = "账户id") @PathVariable Long accountId) {
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
     *
     * @param accountId
     * @param taskId
     * @return
     */
    @PostMapping(value = "/task/complete")
    @ApiOperation(notes = "account complete task", httpMethod = "POST", value = "账户完成任务，增加对应能量值")
    public Integer complete(@ApiParam(required = true, value = "账户id") @RequestParam("accountId") Long accountId,
                            @ApiParam(required = true, value = "任务id") @RequestParam("taskId") Long taskId) {
        return accountService.completeTask(accountId, taskId);
    }

    /**
     * 账户实名认证
     *
     * @param accountId
     * @param name
     * @param identityCard
     */
    @ApiOperation(notes = "account certification", httpMethod = "POST", value = "账户实名认证")
    @PostMapping(value = "/accounts/certification")
    public void certification(@ApiParam(required = true, value = "账户id") @RequestParam(name = "accountId") Long accountId,
                              @ApiParam(required = true, value = "姓名") @RequestParam(name = "name") String name,
                              @ApiParam(required = true, value = "身份证号") @RequestParam(name = "identityCard") String identityCard) {
        accountService.certification(accountId, name, identityCard);
    }


}
