package com.xhyan.zero.cloud.account.mapper;

import com.xhyan.zero.cloud.account.model.Account;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 账户数据库操作
 *
 * @author xhyan
 */
public interface AccountMapper extends Mapper<Account> {

    /**
     * 所有能量的总和
     * @return
     */
    @Select("SELECT SUM(energy) FROM account")
    Integer sumOfEnergy();
}