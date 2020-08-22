package com.bbbscxy.modules.entity;

import com.bbbscxy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
public class MallOrder extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 店铺ID
     */
    private Long storeId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 订单总价
     */
    private Long totalMoney;

    /**
     * 优惠金额
     */
    private Long discountMoney;

    /**
     * 运费
     */
    private Long freightMoney;

    /**
     * 实际金额
     */
    private Long realMoney;

    /**
     * 实付金额
     */
    private Long payMoney;

    /**
     * 订单状态（0待支付 1待发货 2待收货 3已完成）
     */
    private String status;

    /**
     * 支付时间
     */
    private Date payTime;

}
