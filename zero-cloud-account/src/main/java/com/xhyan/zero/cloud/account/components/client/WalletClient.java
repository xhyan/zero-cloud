package com.xhyan.zero.cloud.account.components.client;

import com.xhyan.zero.wallet.api.WalletApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 钱包相关操作API
 *
 * @author xhyan
 */
@FeignClient(serviceId = "zero-cloud-wallet", fallback = MemberClientFallback.class)
public interface WalletClient extends WalletApi {

}
