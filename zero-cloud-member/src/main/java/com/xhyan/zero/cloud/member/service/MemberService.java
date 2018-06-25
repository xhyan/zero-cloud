package com.xhyan.zero.cloud.member.service;

import com.xhyan.zero.cloud.member.mapper.MemberMapper;
import com.xhyan.zero.cloud.member.model.Member;
import com.xhyan.zero.member.dto.MemberDTO;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员服务
 *
 * @author xhyan
 */
@Service
public class MemberService{

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ConfigurableMapper mapper;

    public void save(MemberDTO dto){
        memberMapper.insertSelective(mapper.map(dto, Member.class));
    }
}
