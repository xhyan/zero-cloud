package com.xhyan.zero.cloud.account.model;

import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.copier.annotations.Copier;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 任务信息
 *
 * @author xhyan
 */
@Data
@Copier(mapClass = TaskDTO.class)
@Table(name = "task")
public class Task extends BaseModel {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型:1 内部  2 外部
     */
    private Integer type;

    /**
     * 任务活力值
     */
    private Integer energy;

    /**
     * 任务状态：1 有效  -1 无效
     */
    private Integer status;

    /**
     * 扩展字段
     */
    private String extend;

}