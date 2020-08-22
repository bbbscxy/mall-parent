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
public class MallOrderAddress extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 收货人
     */
    private String receiverName;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String phone;

}
