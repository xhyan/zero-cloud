package com.xhyan.zero.cloud.common.mapper;

import com.xhyan.zero.cloud.common.mapper.provider.CustomMySqlProvider;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 自定义MySql Mapper接口,扩展MySql的其他操作，支持批量replace into
 *
 * @param <T> 不能为空
 * @author yanliwei
 */
public interface CustomMySqlMapper<T>{
    /**
     * 批量替换，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     *
     * @param t
     * @return
     */
    @Options(useGeneratedKeys = true)
    @InsertProvider(type = CustomMySqlProvider.class, method = "dynamicSQL")
    int replace(T t);


    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true)
    @InsertProvider(type = CustomMySqlProvider.class, method = "dynamicSQL")
    int replaceIntoList(List<T> recordList);
}
