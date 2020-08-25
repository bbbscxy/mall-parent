package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.model.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统用户数据层
 */
@Repository
public interface SysUserDao extends CrudDao<SysUser> {

    /**
     * 根据用户名查询用户
     * @param loginName
     */
    SysUser getSysUserByLoginName(String loginName);

    /**
     * 查询用户权限
     * @param id
     */
    List<String> getUserPermissionList(String id);

    /**
     * 删除角色
     */
    void deleteUserRoleByUserId(String id);

    /**
     * 新增角色
     */
    void saveUserRole(SysUserRole sysUserRole);
}