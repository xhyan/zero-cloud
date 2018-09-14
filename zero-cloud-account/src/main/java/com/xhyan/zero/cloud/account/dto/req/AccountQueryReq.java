package com.xhyan.zero.cloud.account.dto.req;

import com.xhyan.zero.cloud.common.dto.Sortable;
import com.xhyan.zero.cloud.common.dto.ZeroPageReq;
import lombok.Data;

/**
 * 账户查询请求对象
 *
 * @author xhyan
 */
@Data
public class AccountQueryReq extends ZeroPageReq implements Sortable {
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 手机号
     */
    private String mobile;
}
