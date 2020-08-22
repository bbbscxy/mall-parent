package com.bbbscxy.modules.entity;

import java.util.Date;
import com.bbbscxy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bbbscxy
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MallGoods extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品简介
     */
    private String subName;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 商品价格（单位分）
     */
    private Long price;

    /**
     * 归属分类
     */
    private Long goodsTypeId;

    /**
     * 状态（0下架 1上架）
     */
    private String status;

    /**
     * 上架时间
     */
    private Date onSaleTime;

    /**
     * 下架时间
     */
    private Date offSaleTime;

    /**
     * 销量
     */
    private Integer saleCount;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 店铺ID
     */
    private Long storeId;

}
