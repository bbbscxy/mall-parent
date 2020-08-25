package com.vim.common.constants;

/**
 * @作者 Administrator
 * @时间 2019-07-29 16:25
 * @版本 1.0
 * @说明 缓存相关变量值
 */
public class SysCacheConstants {

    /**
     * ehcache name列表
     */
    public interface EHCACHE_NAME{
        String USER = "user";
        String CONFIG = "config";
        String DICT = "dict";
        String MENU = "menu";
    }

    /**
     * ehcache key列表
     */
    public interface EHCACHE_KEY{
        //用户拥有的菜单缓存前缀
        String USER_MENU_LIST = "user_menu_list_";
    }

}
