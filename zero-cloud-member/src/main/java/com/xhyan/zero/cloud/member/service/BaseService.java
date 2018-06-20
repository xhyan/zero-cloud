package com.xhyan.zero.cloud.member.service;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * 基本服务类
 *
 * @author xhyan
 * @param <T>
 */
public class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;


    public T findById(Long id) {

        return mapper.selectByPrimaryKey(id);
    }
}
