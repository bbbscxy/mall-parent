package com.vim.modules.sys.controller;

import com.vim.common.base.BaseController;
import com.vim.common.constants.Global;
import com.vim.common.constants.SysConfigConstants;
import com.vim.common.constants.SysDictConstants;
import com.vim.modules.sys.model.SysArea;
import com.vim.modules.sys.model.SysAreaTree;
import com.vim.modules.sys.service.SysAreaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.vim.common.constants.Global.TREE_ROOT_ID;

/**
 * @作者 Administrator
 * @时间 2019-07-23 16:30:55
 * @版本 1.0
 * @说明 系统配置控制层
 */
@Controller
@RequestMapping(value = "/sysArea")
public class SysAreaController extends BaseController{

    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 爬虫
     */
    @RequestMapping(value = "/initAreaData")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> initAreaData() throws Exception{
        List<SysArea> areaList = new ArrayList<>();
        Document doc = Jsoup.connect(Global.getConfigValue(SysConfigConstants.KEY.SYS_AREA_HTML_URL)).maxBodySize(0).get();
        Elements trs = doc.getElementsByTag("tr");
        List<String> specialProvince1 = new ArrayList<>();
        List<String> specialProvince2 = new ArrayList<>();

        specialProvince1.add("北京市");
        specialProvince1.add("上海市");
        specialProvince1.add("天津市");
        specialProvince1.add("重庆市");

        specialProvince2.add("台湾省");
        specialProvince2.add("香港特别行政区");
        specialProvince2.add("澳门特别行政区");

        String currentProvinceId = "";
        String currentCityId = "";

        String parentId = "";
        for(int i=3; i<trs.size(); i++){
            Element tr = trs.get(i);
            Elements tds = tr.getElementsByTag("td");
            if(tds.size() > 3){
                String code = tds.get(1).text();
                String name = tds.get(2).text();
                String level = "0";
                if(StringUtils.isNumeric(code)){
                    String preCode = "";
                    boolean isSpecial = false;
                    if(i != 3){
                        preCode = trs.get(i-1).getElementsByTag("td").get(1).text();
                    }
                    //第一个省
                    if(StringUtils.isBlank(currentProvinceId)){
                        currentProvinceId = code.substring(0,2);
                    }
                    //下一个省
                    if(!code.substring(0,2).equals(currentProvinceId)){
                        currentProvinceId = code.substring(0,2);
                    }
                    //1.直辖市处理
                    if(specialProvince1.contains(name)){
                        level = "0";
                        //生成城区
                        SysArea area = createArea(currentProvinceId+"0100", "城区", currentProvinceId+"0000", "1");
                        areaList.add(area);
                        currentCityId = currentProvinceId+"0100";
                        parentId = "0";
                        isSpecial = true;
                    }else if(specialProvince2.contains(name)){
                        level = "0";
                        //特殊直辖市
                        parentId = "0";
                        //生成2个城区
                        SysArea area = createArea(currentProvinceId+"0100", "城区", code, "1");
                        areaList.add(area);
                        area = createArea(currentProvinceId+"0101", "城区", currentProvinceId+"0100", "2");
                        areaList.add(area);
                        isSpecial = true;
                    }else if("0000".equals(code.substring(2, code.length()))){
                        //省
                        level = "0";
                        parentId = "0";
                    }else if("00".equals(code.substring(4, code.length()))){
                        //市
                        level = "1";
                        parentId = currentProvinceId+"0000";
                        currentCityId = code;
                        isSpecial = false;
                    }else{
                        //区
                        level = "2";
                        parentId = currentCityId;
                        //直辖区
                        if(!isSpecial && !currentCityId.substring(0,4).equals(code.substring(0,4))){
                            level = "1";
                            parentId = currentProvinceId+"0000";
                            //生成县区
                            SysArea area = createArea(code+"1", "县区", code, "2");
                            areaList.add(area);
                        }
                    }
                    SysArea area = createArea(code, name, parentId, level);
                    areaList.add(area);
                }
            }
        }
        sysAreaService.initAreaData(areaList);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    private SysArea createArea(String id, String name, String parentId, String level){
        SysArea area = new SysArea();
        area.setId(id);
        area.setParentId(parentId);
        area.setName(name);
        area.setLevel(level);
        Date date = new Date();
        area.setCreateBy(Global.USER_ADMIN_ID);
        area.setCreateDate(date);
        area.setUpdateBy(Global.USER_ADMIN_ID);
        area.setUpdateDate(date);
        area.setDelFlag(SysDictConstants.DEL_FLAG.NO.getValue());
        return area;
    }

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:area:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/area/list";
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:area:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dataList(@RequestParam(value = "nodeid", required = false) String parentId,
                                        @RequestParam(value = "areaName", defaultValue = "") String areaName){
        List<SysAreaTree> data = new ArrayList<>();
        //1.查询子节点列表
        SysArea findArea = new SysArea();
        findArea.setName(StringUtils.isBlank(parentId) ? areaName : "");
        findArea.setParentId(StringUtils.isBlank(parentId) ? (StringUtils.isBlank(areaName) ? TREE_ROOT_ID : "") : parentId);
        List<SysArea> areaList = sysAreaService.findList(findArea);
        for(SysArea area:areaList){
            //2.构建节点
            SysAreaTree node = new SysAreaTree();
            node.setId(Long.valueOf(area.getId()));
            node.setParentId(Long.valueOf(area.getParentId()));
            node.setLevel(Long.valueOf(area.getLevel()));
            node.setName(area.getName());
            node.setCode(area.getId());
            node.setExpanded(false);
            //3.查询是否还有子节点
            findArea = new SysArea();
            findArea.setParentId(area.getId());
            node.setLeaf(sysAreaService.findList(findArea).size() > 0 ? false : true);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

}
