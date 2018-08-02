package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.common.AccountConstant;
import com.xhyan.zero.cloud.account.mapper.AccountMapper;
import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.wallet.api.WalletApi;
import java.math.BigDecimal;
import java.util.List;
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
    private WalletApi walletApi;
    @Autowired
    private AccountMapper accountMapper;

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
            walletApi.translate(item.getId(),
                perEnergy.multiply(new BigDecimal(item.getEnergy())).setScale(2));
        });
    }
}
