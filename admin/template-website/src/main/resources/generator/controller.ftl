package ${packageName}.modules.${packageModuleName}.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.common.base.BaseController;
import ${packageName}.common.utils.BeanValidatorUtils;
import ${packageName}.modules.${packageModuleName}.model.${moduleClassName};
import ${packageName}.modules.${packageModuleName}.service.${moduleClassName}Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @作者 Administrator
* @时间 ${commentTime}
* @版本 1.0
* @说明
*/
@Controller
@RequestMapping(value = "/${moduleName}")
public class ${moduleClassName}Controller extends BaseController{

    @Autowired
    private ${moduleClassName}Service ${moduleName}Service;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "${packageModuleName}:${moduleName}:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "${packageModuleName}/${moduleName}/list";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "${packageModuleName}:${moduleName}:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<${moduleClassName}> dataList(${moduleClassName} ${moduleName}){
        PageHelper.startPage(${moduleName}.getPageNum(), ${moduleName}.getPageSize());
        return ${moduleName}Service.findPageList(${moduleName});
    }

    /**
    * 编辑页面
    */
    @RequiresPermissions(value = "${packageModuleName}:${moduleName}:form")
    @RequestMapping(value = "/form")
    public String form(${moduleClassName} ${moduleName}, Model model){
        model.addAttribute("${moduleName}", StringUtils.isNoneBlank(${moduleName}.getId()) ? ${moduleName}Service.get(${moduleName}.getId()) : new ${moduleClassName}());
        return "${packageModuleName}/${moduleName}/form";
    }

    /**
    * 更新数据
    */
    @RequiresPermissions(value = "${packageModuleName}:${moduleName}:form")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(${moduleClassName} ${moduleName}){
        ${moduleName}Service.saveOrUpdate(${moduleName});
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
    * 删除
    */
    @RequiresPermissions(value = "${packageModuleName}:${moduleName}:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(${moduleClassName} ${moduleName}){
        ${moduleName}Service.delete(${moduleName}.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}