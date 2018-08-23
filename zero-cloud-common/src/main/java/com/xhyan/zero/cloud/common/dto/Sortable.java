package com.xhyan.zero.cloud.common.dto;

import java.util.List;
import org.assertj.core.util.Lists;

/**
 * 排序接口
 *
 * @author xhyan
 */
public interface Sortable {

    /**
     * 排序字段
     * @return
     */
    default String sortField(){
        return "id";
    }

    /**
     * 排序方式
     * @return
     */
    default String sortMethod(){
        return "DESC";
    }

    /**
     * 所有可排序字段
     * @return
     */
    default List<String> allSortableFields(){
        return Lists.newArrayList();
    }


}
