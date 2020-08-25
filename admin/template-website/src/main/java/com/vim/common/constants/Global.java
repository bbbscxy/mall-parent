package com.vim.common.constants;

import com.vim.modules.sys.model.SysConfig;
import com.vim.modules.sys.model.SysDict;
import com.vim.modules.sys.service.SysConfigService;
import com.vim.modules.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-18 20:21
 * @版本 1.0
 * @说明 静态变量
 */
@Component
public class Global {

    private static Global global;
    @PostConstruct
    public void init() {
        global = this;
    }

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SysDictService sysDictService;

    /**
     * 代码生成器需要过滤的表
     */
    public static final List<String> filterTableList = new ArrayList<>();

    static {
        filterTableList.add("sys_user");
        filterTableList.add("sys_role");
        filterTableList.add("sys_menu");
        filterTableList.add("sys_user_role");
        filterTableList.add("sys_role_menu");
        filterTableList.add("sys_config");
        filterTableList.add("sys_dict");
        filterTableList.add("sys_login_log");
        filterTableList.add("sys_msg");
        filterTableList.add("sys_user_msg");
        filterTableList.add("sys_area");
        filterTableList.add("sys_dept");
        filterTableList.add("sys_operate_log");
        filterTableList.add("gen_table");
        filterTableList.add("gen_table_column");
    }

    /**
     * 管理员主键ID
     */
    public static final String USER_ADMIN_ID = "1";
    /**
     * 树顶级ID
     */
    public static final String TREE_ROOT_ID = "0";

    /**
     * 获取配置信息
     * @param code 配置标识
     */
    public static String getConfigValue(String code){
        SysConfig sysConfig = global.sysConfigService.findConfigByCode(code);
        return sysConfig == null ? null : sysConfig.getValue();
    }

    /**
     * 获取字典列表
     * @param code 字典标识
     */
    public static List<SysDict> getDictList(String code){
        return global.sysDictService.getDictListByCode(code);
    }
}
