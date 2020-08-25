package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.Global;
import com.vim.common.constants.SysConfigConstants;
import com.vim.common.utils.CapitalCamelUtils;
import com.vim.common.utils.FreemarkerUtils;
import com.vim.modules.sys.dao.GenTableDao;
import com.vim.modules.sys.model.GenTable;
import com.vim.modules.sys.model.GenTableColumn;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明 代码生成
*/
@Service
public class GenTableService extends CrudService<GenTableDao, GenTable> {

    private static final Logger logger = LoggerFactory.getLogger(GenTableService.class);

    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
     * 初始化所有表
     */
    @Transactional
    public void initTableList() {
        //1.清空表数据
        dao.clear();
        genTableColumnService.clear();

        //2.初始化所有的表
        List<GenTable> tableList = dao.tableList();
        for(GenTable table:tableList){
            //3.过滤掉系统表
            if(!Global.filterTableList.contains(table.getName())){
                super.save(table);
                //4.初始化所有的表字段
                List<GenTableColumn> tableColumnList = genTableColumnService.tableColumnList(table.getName());
                for(GenTableColumn tableColumn:tableColumnList){
                    tableColumn.setTableId(table.getId());
                    genTableColumnService.save(tableColumn);
                }
            }
        }
    }

    /**
     * 预览
     */
    public Map<String, String> preRender(String id){
        String savePath = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_SAVE_PATH);
        String packageName = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_PACKAGE_NAME);
        String moduleName = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_MODULE_NAME);
        return renderTable(savePath, packageName, moduleName, get(id), true);
    }

    /**
     * 生成所有文件
     */
    public void renderAll(){
        String savePath = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_SAVE_PATH);
        String packageName = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_PACKAGE_NAME);
        String moduleName = Global.getConfigValue(SysConfigConstants.KEY.SYS_GEN_MODULE_NAME);

        //1.创建文件夹
        createDirectory(savePath);

        //2.生成文件
        List<GenTable> tableList = dao.findList(new GenTable());
        for(GenTable table:tableList){
            renderTable(savePath, packageName, moduleName, table, false);
        }
    }

    /**
     * 创建文件夹
     */
    private void createDirectory(String savePath){
        File file = new File(savePath + "/mapper");
        if (!file.exists()){ file.mkdirs(); }

        file = new File(savePath + "/model");
        if (!file.exists()){ file.mkdirs(); }

        file = new File(savePath + "/dao");
        if (!file.exists()){ file.mkdirs(); }

        file = new File(savePath + "/service");
        if (!file.exists()){ file.mkdirs(); }

        file = new File(savePath + "/controller");
        if (!file.exists()){ file.mkdirs(); }

        file = new File(savePath + "/html");
        if (!file.exists()){ file.mkdirs(); }
    }

    /**
     * 生成代码
     * @param savePath      文件保存位置
     * @param packageName   包名
     * @param moduleName    模块名称
     */
    private Map<String, String> renderTable(String savePath, String packageName, String moduleName, GenTable table, boolean isPreview) {
        String charset = "utf-8";
        Map<String, String> contentList = new HashMap<>();

        //1.查询所有表字段
        GenTableColumn findColumn = new GenTableColumn();
        findColumn.setTableId(table.getId());
        List<GenTableColumn> tableColumnList = genTableColumnService.findList(findColumn);

        //2.放置模型数据
        HashMap<String, Object> model = new HashMap<>();
        model.put("packageName", packageName);//包名
        model.put("packageModuleName", moduleName);  //模块名称
        model.put("tableName", table.getName());              //表名称 hello_world
        model.put("moduleName", CapitalCamelUtils.toCamelCase(table.getName()));                //表名称java表示, helloWorld
        model.put("moduleClassName", CapitalCamelUtils.toCapitalizeCamelCase(table.getName())); //表名称java表示, HelloWorld
        model.put("tableColumnList", tableColumnList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.put("commentTime", df.format(new Date()));
        try {
            //3.选择模板文件
            Configuration cfg = FreemarkerUtils.buildConfiguration("classpath:/generator");

            String baseFileName = CapitalCamelUtils.toCapitalizeCamelCase(table.getName());

            //4.生成 xml 文件
            Template template = cfg.getTemplate("sql.ftl", charset);
            contentList.put("sql", FreemarkerUtils.renderString(template, model));
            if(!isPreview){
                FreemarkerUtils.renderFile(template, model, savePath + "/mapper/" + baseFileName + ".xml");
            }

            //5.生成 model 文件
            template = cfg.getTemplate("model.ftl", charset);
            contentList.put("model", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/model/" + baseFileName + ".java");
            }
            //6.生成 dao 文件
            template = cfg.getTemplate("dao.ftl", charset);
            contentList.put("dao", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/dao/" + baseFileName + "Dao.java");
            }
            //7.生成 service 文件
            template = cfg.getTemplate("service.ftl", charset);
            contentList.put("service", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/service/" + baseFileName + "Service.java");
            }
            //8.生成 controller 文件
            template = cfg.getTemplate("controller.ftl", charset);
            contentList.put("controller", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/controller/" + baseFileName + "Controller.java");
            }
            //9.生成 list 文件
            template = cfg.getTemplate("list.ftl", charset);
            contentList.put("list", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/html/" + baseFileName + "List.ftl");
            }
            //10.生成 form 文件
            template = cfg.getTemplate("form.ftl", charset);
            contentList.put("form", FreemarkerUtils.renderString(template, model));
            if(!isPreview) {
                FreemarkerUtils.renderFile(template, model, savePath + "/html/" + baseFileName + "Form.ftl");
            }
        }catch (Exception e){
            logger.error("系统异常!", e);
        }
        return contentList;
    }

}