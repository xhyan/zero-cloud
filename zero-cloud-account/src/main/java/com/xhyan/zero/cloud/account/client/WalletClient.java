package com.xhyan.zero.cloud.account.client;

import com.xhyan.zero.wallet.api.WalletApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(serviceId = "zero-cloud-wallet", fallback = MemberClientFallback.class)
public interface WalletClient extends WalletApi {

}
