package com.vim.modules.sys.controller;

import com.vim.common.constants.SysCacheConstants;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.utils.CookieUtils;
import com.vim.common.utils.EhcacheUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.model.SysMenu;
import com.vim.modules.sys.model.SysUserMsg;
import com.vim.modules.sys.service.SysMenuService;
import com.vim.modules.sys.service.SysUserMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 主页控制层
 */
@Controller
public class HomeController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserMsgService sysUserMsgService;

    /**
     * 将list转为tree
     * @param list
     * @param pid
     * @return
     */
    public static List<SysMenu> getTree(List<SysMenu> list,String pid){
        List<SysMenu> result =  new ArrayList<>();
        for(SysMenu entity : list){
            if(entity.getParentId().equals(pid)){
                List<SysMenu> temp = getTree(list,entity.getId());
                if(temp.size() > 0){
                    entity.setChildMenuList(temp);
                }
                result.add(entity);
            }
        }
        return result;
    }

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response){
        //1.查询缓存中当前用户拥有的所有菜单
        List<SysMenu> menuList = EhcacheUtils.getList(SysCacheConstants.EHCACHE_NAME.MENU, SysCacheConstants.EHCACHE_KEY.USER_MENU_LIST+UserUtils.currentUser().getId());
        if(null == menuList){
            //2.查询数据库菜单数据
            menuList = sysMenuService.getUserMenuList(UserUtils.currentUser().getId());
            //3.将集合转换为树
            menuList = getTree(menuList, "0");
            //4.放入缓存
            EhcacheUtils.put(SysCacheConstants.EHCACHE_NAME.MENU, SysCacheConstants.EHCACHE_KEY.USER_MENU_LIST+UserUtils.currentUser().getId(), menuList);
        }

        //5.设置选中的id
        String topId = CookieUtils.getCookie(request, "topId");
        if(StringUtils.isBlank(topId)){
            topId = menuList.get(0).getId();
            CookieUtils.setCookie(response, "topId", topId);
        }

        //6.获取选中的菜单
        List<SysMenu> selectedList = new ArrayList<>();
        for(SysMenu menu:menuList){
            if(menu.getId().equals(topId)){
                selectedList = menu.getChildMenuList();
                break;
            }
        }

        //7.查询未读消息数量
        SysUserMsg findMsg = new SysUserMsg();
        findMsg.setUserId(UserUtils.currentUser().getId());
        findMsg.setStatus(SysDictConstants.MSG_READ_STATUS.NO.getValue());
        List<SysUserMsg> sysMsgList = sysUserMsgService.findList(findMsg);
        model.addAttribute("topMenuList", menuList);
        model.addAttribute("leftMenuList", selectedList);
        model.addAttribute("msgCount", sysMsgList.size());
        return "index";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "/desktop")
    public String desktop(){
        return "desktop";
    }

    /**
     * 没有权限
     */
    @RequestMapping(value = "/unauthorized")
    public String unauthorized(){
        return "error/unauthorized";
    }
}
