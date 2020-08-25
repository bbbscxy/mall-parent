package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysRole;
import com.vim.modules.sys.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统角色数据层
 */
@Repository
public interface SysRoleDao extends CrudDao<SysRole> {



    /**
     * 保存角色菜单
     * @param sysRoleMenu
     */
    void saveRoleMenu(SysRoleMenu sysRoleMenu);

    /**
     * 删除角色菜单（使用roleId）
     * @param roleId
     */
    void deleteRoleMenuByRoleId(String roleId);

    /**
     * 删除角色菜单（使用menuId）
     * @param menuIds
     */
    void deleteRoleMenuByMenuIds(@Param("menuIds") List<String> menuIds);

    /**
     * 删除用户角色(使用roleId)
     * @param roleId
     */
    void deleteUserRoleByRoleId(String roleId);

    /**
     * 删除用户角色(使用userId)
     * @param userId
     */
    void deleteUserRoleByUserId(String userId);

    /**
     * 查询当前角色被使用的用户ID列表
     * @param roleId
     */
    List<String> findUserIdsByRoleId(String roleId);

    /**
     * 查询当前用户拥有的角色ID列表
     * @param userId
     */
    List<String> findRoleIdsByUserId(String userId);
}