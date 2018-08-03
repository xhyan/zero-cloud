package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.dto.RewardsDTO;
import com.xhyan.zero.cloud.account.service.RewardsDispatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 奖励发放相关接口
 *
 * @author xhyan
 */
@RestController
@Api(value = "/rewards", description = "奖励相关服务")
public class RewardsController {

    @Autowired
    private RewardsDispatchService rewardsDispatchService;

    /**
     * 根据账户id查询待发放奖励
     */
    @ApiOperation(notes = "query rewards", httpMethod = "GET", value = "查询账户可领取奖励")
    @GetMapping(value = "/rewards/account/{accountId}")
    public List<RewardsDTO> queryRewards( @ApiParam(required = true, name = "账户id") @PathVariable("accountId") Long accountId) {
        return rewardsDispatchService.queryByAccount(accountId);
    }
}
