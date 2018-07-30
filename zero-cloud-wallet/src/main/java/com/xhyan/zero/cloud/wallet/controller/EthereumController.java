package com.xhyan.zero.cloud.wallet.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.xhyan.zero.cloud.wallet.config.ethereum.EthereumBean;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 以太坊controller
 *
 * @author yanliwei
 */
@RestController
public class EthereumController {

    @Autowired
    EthereumBean ethereumBean;

    @RequestMapping(value = "/bestBlock", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBestBlock() throws IOException {
        return ethereumBean.getBestBlock();
    }
}
