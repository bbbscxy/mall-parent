package com.bbbscxy.modules.entity;

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
public class MallGoods extends BaseEntity{

    /**
     * 商品名称
     */
    private String name;
}
