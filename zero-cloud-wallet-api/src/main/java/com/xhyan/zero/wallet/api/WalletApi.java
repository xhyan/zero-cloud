package com.xhyan.zero.wallet.api;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 钱包信息API
 * @author xhyan
 */
public interface WalletApi {

    /**
     * 查询账户对应钱包的余额
     * @param accountId
     * @return
     */
    @GetMapping(value = "/wallet/balance/{accountId}")
    Long queryBalance(@PathVariable("accountId") Long accountId);

    /**
     * 钱包转账
     * @param accountId
     * @param amount
     * @return
     */
    @PostMapping(value = "/wallet/balance")
    Long translate(@PathVariable("accountId") Long accountId, @PathVariable("amount") BigDecimal amount);
}
