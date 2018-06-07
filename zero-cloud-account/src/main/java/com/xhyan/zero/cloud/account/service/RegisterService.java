package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.model.Account;
import org.springframework.stereotype.Service;

/**
 * 账户注册服务
 *
 * @author yanliwei
 */
@Service
public class RegisterService extends BaseService<Account> {

    public Boolean register(AccountDTO dto) {
        Account account = new Account();
        account.setType(1);
        account.setStatus(1);
        return this.save(account) > 0;
    }


}
