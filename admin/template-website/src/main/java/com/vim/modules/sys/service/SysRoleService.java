package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.SysCacheConstants;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.utils.EhcacheUtils;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.dao.SysRoleDao;
import com.vim.modules.sys.model.SysRole;
import com.vim.modules.sys.model.SysRoleMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统角色业务层
 */
@Service
public class SysRoleService extends CrudService<SysRoleDao, SysRole> {

    /**
     * 查询当前用户拥有的角色ID列表
     * @param userId
     */
    public List<String> findRoleIdsByUserId(String userId){
        return dao.findRoleIdsByUserId(userId);
    }

    @Transactional
    public void saveOrUpdateRole(SysRole sysRole, String menuIds) {
        //1.清除所有用户菜单列表缓存
        UserUtils.clearAllUserMenuListCache();
        //2.清除用户权限信息,查询当前角色被使用的用户列表
        List<String> userIds = dao.findUserIdsByRoleId(sysRole.getId());
        for(String userId:userIds){
            UserUtils.clearCachedAuthorization(userId);
        }
        if(StringUtils.isNoneBlank(sysRole.getId())){
            super.update(sysRole);
        }else{
            super.save(sysRole);
        }
        //3.删除角色菜单
        dao.deleteRoleMenuByRoleId(sysRole.getId());
        //4.新增角色菜单
        String[] ids = menuIds.split(",");
        for(String menuId:ids){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setId(EncryUtils.primaryKey());
            sysRoleMenu.setRoleId(sysRole.getId());
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setCreateBy(UserUtils.currentUser().getId());
            sysRoleMenu.setCreateDate(new Date());
            sysRoleMenu.setDelFlag(SysDictConstants.DEL_FLAG.NO.getValue());
            dao.saveRoleMenu(sysRoleMenu);
        }
    }

    /**
     * 删除角色
     * @param id
     */
    @Transactional
    @Override
    public void delete(String id) {
        //1.清除用户菜单列表缓存
        EhcacheUtils.clear(SysCacheConstants.EHCACHE_NAME.MENU);
        //2.清除用户权限信息,查询当前角色被使用的用户列表
        List<String> userIds = dao.findUserIdsByRoleId(id);
        for(String userId:userIds){
            UserUtils.clearCachedAuthorization(userId);
        }
        //3.删除角色
        super.delete(id);
        //4.删除用户角色
        dao.deleteUserRoleByRoleId(id);
        //5.删除角色权限
        dao.deleteRoleMenuByRoleId(id);
    }
}