package com.xhyan.zero.cloud.account.mapper;

import com.xhyan.zero.cloud.account.model.RewardsDispatch;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 奖励发放数据库操作
 *
 * @author xhyan
 */
public interface RewardsDispatchMapper extends Mapper<RewardsDispatch> {

    /**
     * 根据账户id查询账户待领取奖励
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM rewards_dispatch WHERE account_id = #{accountId}")
    List<RewardsDispatch> queryByAccountId(@Param("accountId") Long accountId);

    /**
     * 根据账户id查询已发放奖励的数量
     * @param accountId
     * @return
     */
    @Select("SELECT COUNT(id) FROM rewards_dispatch WHERE account_id = #{accountId}")
    Integer countByAccountId(@Param("accountId") Long accountId);
}