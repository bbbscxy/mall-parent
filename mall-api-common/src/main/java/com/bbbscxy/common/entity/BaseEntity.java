package com.bbbscxy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

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
}