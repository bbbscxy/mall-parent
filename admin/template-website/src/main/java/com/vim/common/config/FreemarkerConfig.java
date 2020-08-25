package com.vim.common.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.vim.common.freemarker.DictListTemplate;
import com.vim.common.freemarker.UserInfoTemplate;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

/**
 * @作者 Administrator
 * @时间 2019-07-26 11:34
 * @版本 1.0
 * @说明 freemarker 变量
 */
@Configuration
public class FreemarkerConfig {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setVariableConfiguration() throws TemplateModelException {
        //路径变量
        configuration.setSharedVariable("ctx", servletContext.getContextPath());
        configuration.setSharedVariable("ctxStatic", servletContext.getContextPath() + "/statics");
        //字典数据
        configuration.setSharedVariable("dictList", new DictListTemplate());
        //当前用户
        configuration.setSharedVariable("currentUser", new UserInfoTemplate());
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
