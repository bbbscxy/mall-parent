package com.vim.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Map;

public class FreemarkerUtils {

    private FreemarkerUtils(){}

    private static final  Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

    /**
     * 使用模板字符串
     * @param model
     */
    public static String renderString(Template template, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            logger.error("freemarker工具类渲染异常!", e);
        }
        return null;
    }

    /**
     * 配置模板文件位置
     * @param directory
     * @return
     * @throws IOException
     */
    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

    /**
     * 使用模板文件
     * @param template
     * @param model
     */
    public static void renderFile(Template template, Map<String, Object> model, String saveFile) {
        try {
            FileWriter out = new FileWriter(new File(saveFile));
            template.process(model, out);
        } catch (Exception e) {
            logger.error("freemarker工具类渲染异常!", e);
        }
    }

}