package com.xhyan.zero.wallet.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
