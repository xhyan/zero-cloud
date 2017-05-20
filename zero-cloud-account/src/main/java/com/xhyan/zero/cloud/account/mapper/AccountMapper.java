package com.xhyan.zero.cloud.account.mapper;

import com.xhyan.zero.cloud.account.model.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountMapper extends Mapper<Account> {
}