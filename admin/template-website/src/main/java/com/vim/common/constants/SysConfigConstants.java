package com.vim.common.constants;

/**
 * @作者 Administrator
 * @时间 2019-07-29 16:26
 * @版本 1.0
 * @说明 配置相关key
 */
public class SysConfigConstants {

    /**
     * 配置表key列表
     */
    public interface KEY{
        //默认密码
        String SYS_DEFAULT_PASSWORD = "sys.default.password";

        //代码生成
        String SYS_GEN_SAVE_PATH = "sys.generator.savePath";
        String SYS_GEN_PACKAGE_NAME = "sys.generator.packageName";
        String SYS_GEN_MODULE_NAME = "sys.generator.moduleName";

        //省市区爬虫
        String SYS_AREA_HTML_URL = "sys.area.html.url";
    }

    /**
     * ftp目录
     */
    public interface FTP_DIR{
        String IMAGE = "/images/";
    }
}
