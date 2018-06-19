package com.xhyan.zero.account;

import com.xhyan.zero.cloud.account.AccountApplication;
import com.xhyan.zero.cloud.account.mapper.AccountMapper;
import com.xhyan.zero.cloud.account.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yanliwei on 2017/7/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
public class MapperTest {

  @Autowired
  AccountMapper accountMapper;

  @Test
  public void testInsert(){
    Account account = new Account();
    account.setEmail("222222334");
    account.setLoginName("xhyan11");
    account.setLoginPwd("1234");
    account.setTradePwd("4321");
    account.setStatus(1);
    int i = accountMapper.insertSelective(account);
    System.out.println(i);
  }
}
