package com.xhyan.zero.cloud.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

public class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    public T findById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
