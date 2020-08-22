package com.bbbscxy.modules.entity;

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
public class MallGoodsType extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父级分裂
     */
    private Long pid;

    /**
     * 分类图标
     */
    private String logo;

    /**
     * 分类描述
     */
    private String desc;

    /**
     * 路径
     */
    private String path;

    /**
     * 商品数量
     */
    private Integer goodsCount;

}
