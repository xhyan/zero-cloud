package com.xhyan.zero.cloud.account.mapper;

import com.xhyan.zero.cloud.account.model.AccountTask;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 账户任务数据库操作
 *
 * @author xhyan
 */
public interface AccountTaskMapper extends Mapper<AccountTask> {

    @Select("SELECT * FROM account_task WHERE account_id = #{accountId}")
    List<AccountTask> queryByAccountId(@Param("accountId") Long accountId);
}