package com.xhyan.zero.cloud.member.model;

import java.util.Date;
import javax.persistence.Column;
import lombok.Data;

/**
 * Base模型对象
 *
 * @author xhyan
 */
@Data
public class BaseModel {

    /**
     * 创创create date
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modify")
    private Date gmtModify;
}
