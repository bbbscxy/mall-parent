package com.vim.common.shiro;

import com.vim.common.constants.Global;
import com.vim.common.queue.MsgPushThread;
import com.vim.modules.sys.model.SysMenu;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.service.SysMenuService;
import com.vim.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:57
 * @版本 1.0
 * @说明 Shiro 用户权限验证
 */
public class UserRealm extends AuthorizingRealm {

    private static final  Logger logger = LoggerFactory.getLogger(MsgPushThread.class);

    @Autowired
    @Lazy
    private SysUserService sysUserService;
    @Autowired
    @Lazy
    private SysMenuService sysMenuService;

    /**
     * 权限校验
     * @param principal
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //1.取出主体信息，注意此处的 User 必须实现 Serializable 序列化
        String userId= (String) principal.getPrimaryPrincipal();

        //2.添加权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(Global.USER_ADMIN_ID.equals(userId)){
            //管理员,查询所有权限
            List<SysMenu> menuList = sysMenuService.findList(new SysMenu());
            for(SysMenu menu:menuList){
                if(StringUtils.isNotBlank(menu.getPermission())){
                    simpleAuthorizationInfo.addStringPermission(menu.getPermission());
                }
            }
        }else{
            //普通用户,查询权限信息
            List<String> permissions = sysUserService.getUserPermissionList(userId);
            for(String permission:permissions){
                simpleAuthorizationInfo.addStringPermission(permission);
            }
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 用户名和密码校验
     * @param token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //1.前端 token 信息
        String username = ((UserPasswdToken) token).getUsername();

        //2.后端 token 信息
        SysUser user = sysUserService.getSysUserByLoginName(username);
        if(null == user){
            throw new AuthenticationException("账号不存在!");
        }else{
            //3.保存的主体对象，此处是User
            return new SimpleAuthenticationInfo(user.getId(),
                    user.getPassword(), ByteSource.Util.bytes(user.getLoginName()), getName());
        }
    }

    /**
     * 清空权限缓存
     */
    public static void clearCachedAuthorization(String userId){
        try {
            RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            UserRealm userRealm = (UserRealm)rsm.getRealms().iterator().next();
            Subject subject = SecurityUtils.getSubject();
            String realmName = subject.getPrincipals().getRealmNames().iterator().next();
            SimplePrincipalCollection principals = new SimplePrincipalCollection(userId, realmName);
            subject.runAs(principals);
            userRealm.getAuthorizationCache().remove(subject.getPrincipals());
            subject.releaseRunAs();
        }catch (Exception e){
            logger.error("系统异常!", e);
        }
    }
}