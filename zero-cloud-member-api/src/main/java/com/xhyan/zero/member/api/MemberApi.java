package com.xhyan.zero.member.api;

import com.xhyan.zero.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 会员信息服务api
 *
 * @author xhyan
 */
public interface MemberApi {

    /**
     * 根据会员id获取会员信息
     * @param id
     * @return
     */
    @GetMapping(value = "/member/{id}")
    MemberDTO findOne(@PathVariable("id") Long id);

    /**
     * 根据账户id获取会员信息
     * @param accountId
     * @return
     */
    @GetMapping(value = "/member/account/{accountId}")
    MemberDTO findOneByAccountId(@PathVariable("accountId") Long accountId);
}
