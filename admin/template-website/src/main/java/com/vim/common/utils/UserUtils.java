package com.vim.common.utils;

import com.vim.common.constants.SysCacheConstants;
import com.vim.common.shiro.UserRealm;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:22
 * @版本 1.0
 * @说明 用户信息工具类
 */
@Component
public class UserUtils {

    private static final  Logger logger = LoggerFactory.getLogger(UserUtils.class);

    private static UserUtils userUtils;

    @Autowired
    private SysUserService sysUserService;

    @PostConstruct
    public void init() {
        userUtils = this;
    }

    /**
     * 查询用户信息
     */
    public static SysUser currentUser(){
        SysUser sysUser = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            sysUser = userUtils.sysUserService.get((String) subject.getPrincipal());
            sysUser.setPassword(null);
        }catch (Exception e){
            logger.error("当前用户信息错误!", e);
        }
        return sysUser;
    }

    /**
     * 清除用户权限信息
     * @param userId
     */
    public static void clearCachedAuthorization(String userId){
        UserRealm.clearCachedAuthorization(userId);
    }

    /**
     * 清除用户菜单列表缓存
     */
    public static void clearUserMenuListCache(String userId){
        EhcacheUtils.delete(SysCacheConstants.EHCACHE_NAME.MENU, SysCacheConstants.EHCACHE_KEY.USER_MENU_LIST + userId);
    }

    /**
     * 清除所有用户菜单列表缓存
     */
    public static void clearAllUserMenuListCache(){
        EhcacheUtils.clear(SysCacheConstants.EHCACHE_NAME.MENU);
    }
}
