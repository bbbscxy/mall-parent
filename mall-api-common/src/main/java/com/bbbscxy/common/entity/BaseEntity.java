package com.bbbscxy.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2020-08-20 15:12
 * @版本 1.0
 * @说明 实体类基类
 */
@Data
public class BaseEntity implements Serializable{

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 删除状态（0未删除  1已删除）
     */
    @TableLogic
    private String delFlag;
}
