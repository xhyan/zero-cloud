package com.xhyan.zero.cloud.member.controller;

import com.xhyan.zero.cloud.member.model.Member;
import com.xhyan.zero.cloud.member.service.MemberService;
import com.xhyan.zero.member.api.MemberApi;
import com.xhyan.zero.member.dto.MemberDTO;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员服务控制器
 *
 * @author yanliwei
 */
@RestController
public class MemberController implements MemberApi {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ConfigurableMapper mapper;

    @Override
    public MemberDTO findOne(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);
        return mapper.map(member, MemberDTO.class);
    }

    @Override
    public MemberDTO findOneByAccountId(@PathVariable("accountId") Long accountId) {
        return null;
    }
}
