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
public class MallStoreAccount extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 店铺ID
     */
    private Long storeId;

    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 账号类型（0手机号 1邮箱 2微信）
     */
    private String accountType;

    /**
     * 管理员（0否 1是）
     */
    private String adminFlag;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 状态（0正常 1禁止登录 2注销）
     */
    private String status;

}
