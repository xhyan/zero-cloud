package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.common.AccountConstant;
import com.xhyan.zero.cloud.account.dto.RewardsDTO;
import com.xhyan.zero.cloud.account.mapper.AccountMapper;
import com.xhyan.zero.cloud.account.mapper.RewardsDispatchMapper;
import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.cloud.account.model.RewardsDispatch;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发放奖励服务
 *
 * @author xhyan
 */
@Service
public class RewardsDispatchService {

    @Autowired
    private ConfigurableMapper copier;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RewardsDispatchMapper rewardsDispatchMapper;

    /**
     * 发放奖励
     */
    public void dispatch() {
        //取得当前
        //得到所有能量
        Integer energy = accountMapper.sumOfEnergy();
        BigDecimal perEnergy = BigDecimal
            .valueOf(AccountConstant.REWARDS_AMOUNT_PER_HOURS / energy);
        List<Account> accountList = accountMapper.selectAll();
        accountList.forEach(item -> {
            if (rewardsDispatchMapper.countByAccountId(item.getId()) < AccountConstant.ACCOUNT_MAX_REWARDS_COUNT) {
                //超过20次未领取，将不再发放奖励
                RewardsDispatch dispatch = new RewardsDispatch();
                dispatch.setAccountId(item.getId());
                dispatch.setType(1);
                dispatch.setAmount(
                    BigDecimal.valueOf(item.getEnergy()).multiply(perEnergy).setScale(2));
                rewardsDispatchMapper.insertSelective(dispatch);
            }
        });
    }

    public List<RewardsDTO> queryByAccount(Long accountId) {
        List<RewardsDispatch> rewards = rewardsDispatchMapper.queryByAccountId(accountId);
        return rewards.stream().map(item -> copier.map(item, RewardsDTO.class)).collect(
            Collectors.toList());
    }
}
