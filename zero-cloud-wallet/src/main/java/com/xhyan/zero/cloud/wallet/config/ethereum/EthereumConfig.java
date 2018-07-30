package com.xhyan.zero.cloud.wallet.config.ethereum;

import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EthereumConfig {
    @Bean
    EthereumBean ethereumBean() throws Exception {
        EthereumBean ethereumBean = new EthereumBean();
        Executors.newSingleThreadExecutor().
            submit(ethereumBean::start);

        return ethereumBean;
    }
}
