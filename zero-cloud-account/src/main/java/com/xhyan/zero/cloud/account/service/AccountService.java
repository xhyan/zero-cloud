package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.client.MemberClient;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.member.dto.MemberDTO;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Account Service
 *
 * @author xhyan
 */
@Service
public class AccountService extends BaseService<Account> {

    @Autowired
    private MemberClient memberClient;
    @Autowired
    private ConfigurableMapper copier;

    public void signUp(AccountDTO accountDTO) {
        Account account = copier.map(accountDTO, Account.class);
        account.setType(1);
        account.setStatus(1);
        //保存账户信息
        int result = mapper.insertSelective(account);
        if (result > 0) {
            //保存会员信息
            MemberDTO member = MemberDTO.builder().accountId(account.getId()).availablePoints(0L)
                .level(0).points(0L).status(1).level(1).build();
            memberClient.create(member);
        }

    }

    public AccountDTO findOne(Long id) {
        Account account = mapper.selectByPrimaryKey(id);
        return copier.map(account, AccountDTO.class);
    }
}
