package com.vim.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.vim.common.base.CrudService;
import com.vim.common.constants.SysCacheConstants;
import com.vim.common.constants.SysConfigConstants;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.constants.Global;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.dao.SysRoleDao;
import com.vim.modules.sys.dao.SysUserDao;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.model.SysUserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统用户业务层
 */
@Service
@CacheConfig(cacheNames = SysCacheConstants.EHCACHE_NAME.USER)
public class SysUserService extends CrudService<SysUserDao, SysUser> {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Cacheable(key = "'user_info_'.concat(#id)")
    @Override
    public SysUser get(String id) {
        return super.get(id);
    }

    @Override
    public List<SysUser> findList(SysUser entity) {
        entity.setId(Global.USER_ADMIN_ID);
        return super.findList(entity);
    }

    @Override
    public PageInfo<SysUser> findPageList(SysUser entity) {
        entity.setId(Global.USER_ADMIN_ID);
        return super.findPageList(entity);
    }

    /**
     * 保存用户
     * @param sysUser
     */
    @Override
    public void save(SysUser sysUser) {
        sysUser.setPassword(Global.getConfigValue(SysConfigConstants.KEY.SYS_DEFAULT_PASSWORD));
        sysUser.setPassword(EncryUtils.encryPassword(sysUser.getLoginName(), sysUser.getPassword()));
        super.save(sysUser);
    }

    /**
     * 更新用户
     */
    @Transactional
    @CacheEvict(key = "'user_info_'.concat(#sysUser.id)")
    @Override
    public void update(SysUser sysUser){
        //1.清除用户权限信息
        UserUtils.clearCachedAuthorization(sysUser.getId());
        //2.清除用户菜单列表缓存
        UserUtils.clearUserMenuListCache(sysUser.getId());
        super.update(sysUser);
    }

    @CacheEvict(key = "'user_info_'.concat(#id)")
    @Transactional
    @Override
    public void delete(String id) {
        //1.清除用户权限信息
        UserUtils.clearCachedAuthorization(id);
        //2.清除用户菜单列表缓存
        UserUtils.clearUserMenuListCache(id);
        //3.删除用户
        super.delete(id);
        //4.删除用户角色
        sysRoleDao.deleteUserRoleByUserId(id);
    }

    /**
     * 根据用户名查询用户
     * @param loginName
     */
    public SysUser getSysUserByLoginName(String loginName) {
        return dao.getSysUserByLoginName(loginName);
    }

    /**
     * 查询用户权限
     * @param id
     */
    public List<String> getUserPermissionList(String id) {
        return dao.getUserPermissionList(id);
    }

    /**
     * 编辑用户
     */
    @CacheEvict(key = "'user_info_'.concat(#sysUser.id)")
    @Transactional
    public void saveOrUpdateUser(SysUser sysUser, String roleIds) {
        if(StringUtils.isNoneBlank(sysUser.getId())){
            update(sysUser);
        }else{
            save(sysUser);
        }
        //1.删除用户角色
        dao.deleteUserRoleByUserId(sysUser.getId());
        //2.新增用户角色
        String[] ids = roleIds.split(",");
        for(String roleId:ids){
            if(StringUtils.isNotBlank(roleId)){
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(EncryUtils.primaryKey());
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(roleId);
                sysUserRole.setCreateBy(UserUtils.currentUser().getId());
                sysUserRole.setCreateDate(new Date());
                sysUserRole.setDelFlag(SysDictConstants.DEL_FLAG.NO.getValue());
                dao.saveUserRole(sysUserRole);
            }
        }
    }
}