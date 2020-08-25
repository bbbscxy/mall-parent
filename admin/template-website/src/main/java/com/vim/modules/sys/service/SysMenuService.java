package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.Global;
import com.vim.common.constants.SysCacheConstants;
import com.vim.common.utils.EhcacheUtils;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.dao.SysMenuDao;
import com.vim.modules.sys.dao.SysRoleDao;
import com.vim.modules.sys.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统菜单业务层
 */
@Service
public class SysMenuService extends CrudService<SysMenuDao, SysMenu> {

    @Autowired
    private SysRoleDao sysRoleDao;

    /**
     * 查询用户菜单
     * @param id
     */
    public List<SysMenu> getUserMenuList(String id) {
        if(Global.USER_ADMIN_ID.equals(id)){
            return dao.findList(new SysMenu());
        }else{
            return dao.getUserMenuList(id);
        }
    }

    /**
     * 当前角色拥有的菜单
     * @param roleId
     */
    public List<SysMenu> getRoleMenuList(String roleId) {
        return dao.getRoleMenuList(roleId);
    }

    /**
     * 当前角色拥有的菜单IDS
     * @param roleId
     */
    public List<String> getRoleMenuIds(String roleId) {
        List<String> result = new ArrayList<>();
        dao.getRoleMenuList(roleId).forEach((menu)->{
            result.add(menu.getId());
        });
        return result;
    }

    @Transactional
    @Override
    public void update(SysMenu sysMenu){
        //1.清除用户菜单列表缓存
        EhcacheUtils.clear(SysCacheConstants.EHCACHE_NAME.MENU);
        //2.清除用户权限信息,查询当前菜单被使用的用户列表
        List<String> menuIds = new ArrayList<>();
        menuIds.add(sysMenu.getId());
        List<String> userIds = dao.findUserIdsByMenuIds(menuIds);
        for(String userId:userIds){
            UserUtils.clearCachedAuthorization(userId);
        }
        super.update(sysMenu);
    }

    /**
     * 删除菜单
     * @param id
     */
    @Transactional
    @Override
    public void delete(String id){
        //1.清除用户菜单列表缓存
        EhcacheUtils.clear(SysCacheConstants.EHCACHE_NAME.MENU);
        //2.清除用户权限信息,查询当前菜单被使用的用户列表
        List<String> menuIds = dao.findListWithChildren(id);
        List<String> userIds = dao.findUserIdsByMenuIds(menuIds);
        for(String userId:userIds){
            UserUtils.clearCachedAuthorization(userId);
        }
        //3.删除菜单
        super.delete(id);
        //4.删除角色菜单
        sysRoleDao.deleteRoleMenuByMenuIds(menuIds);
    }

    /**
     * 新增菜单
     * @param sysMenu
     */
    @Transactional
    @Override
    public void save(SysMenu sysMenu) {
        //1.清除用户菜单列表缓存
        EhcacheUtils.clear(SysCacheConstants.EHCACHE_NAME.MENU);
        //2.清除管理员权限信息
        UserUtils.clearCachedAuthorization(Global.USER_ADMIN_ID);
        Date date = new Date();
        sysMenu.setId(EncryUtils.numPrimaryKey());
        sysMenu.setCreateBy(UserUtils.currentUser().getId());
        sysMenu.setCreateDate(date);
        sysMenu.setUpdateBy(UserUtils.currentUser().getId());
        sysMenu.setUpdateDate(date);
        dao.save(sysMenu);
    }
}