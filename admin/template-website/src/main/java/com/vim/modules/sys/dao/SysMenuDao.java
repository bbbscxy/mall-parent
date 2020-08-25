package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统菜单数据层
 */
@Repository
public interface SysMenuDao extends CrudDao<SysMenu> {

    /**
     * 查询用户菜单
     * @param id
     */
    List<SysMenu> getUserMenuList(String id);

    /**
     * 当前角色拥有的菜单
     * @param roleId
     */
    List<SysMenu> getRoleMenuList(String roleId);

    /**
     * 查询当前菜单被使用的用户ID列表
     * @param menuIds
     */
    List<String> findUserIdsByMenuIds(@Param("menuIds") List<String> menuIds);

    /**
     * 查询当前菜单编号及其子编号
     * @param id
     */
    List<String> findListWithChildren(String id);
}