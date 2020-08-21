package com.bbbscxy.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bbbscxy.common.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @作者 Administrator
 * @时间 2020-08-20 15:05
 * @版本 1.0
 * @说明 商品实体类
 */
@Data
@ToString
@TableName("mall_goods")
public class MallGoods extends BaseEntity{

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;
}
