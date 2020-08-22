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
public class MallGoodsResource extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 资源类型
     */
    private String type;

}
