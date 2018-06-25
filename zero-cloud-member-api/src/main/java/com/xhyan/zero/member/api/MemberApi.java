package com.xhyan.zero.member.api;

import com.xhyan.zero.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 会员信息服务api
 *
 * @author xhyan
 */
public interface MemberApi {

    /**
     * 创建会员
     * @param member
     */
    @PostMapping(value = "/member/save")
    void create(@RequestBody MemberDTO member);

    /**
     * 根据会员id获取会员信息
     */
    @GetMapping(value = "/member/{id}")
    MemberDTO findOne(@PathVariable("id") Long id);

    /**
     * 根据账户id获取会员信息
     */
    @GetMapping(value = "/member/account/{accountId}")
    MemberDTO findOneByAccountId(@PathVariable("accountId") Long accountId);
}
