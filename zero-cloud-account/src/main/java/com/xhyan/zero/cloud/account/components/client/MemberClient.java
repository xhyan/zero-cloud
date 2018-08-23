package com.xhyan.zero.cloud.account.components.client;

import com.xhyan.zero.member.api.MemberApi;
import com.xhyan.zero.member.dto.MemberDTO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 会员服务调用客户端
 *
 * @author xhyan
 */
@FeignClient(serviceId = "zero-cloud-member", fallback = MemberClientFallback.class)
public interface MemberClient extends MemberApi {

}

/**
 * 会员服务调用客户端回退处理
 *
 * @author xhyan
 */
class MemberClientFallback implements MemberClient{

    @Override
    public void create(MemberDTO memberDTO) {

    }

    @Override
    public MemberDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MemberDTO findOneByAccountId(Long accountId) {
        return null;
    }
}
