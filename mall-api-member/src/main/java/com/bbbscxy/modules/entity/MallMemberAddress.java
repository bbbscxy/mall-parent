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
public class MallMemberAddress extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 会员ID
     */
    private Long memberId;

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

    /**
     * 默认地址（0否 1是）
     */
    private String defaultFlag;

}
