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
public class MallStore extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册时间
     */
    private Date registerTime;

}
