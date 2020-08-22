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
public class MallMemberLoginAccount extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 会员ID
     */
    private Long memberId;

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

}
