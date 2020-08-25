package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.model.SysUserMsg;
import com.vim.modules.sys.service.SysUserMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-31 16:35:31
 * @版本 1.0
 * @说明
 */
@Controller
@RequestMapping(value = "/sysUserMsg")
public class SysUserMsgController extends BaseController{

    @Autowired
    private SysUserMsgService sysUserMsgService;

    /**
     * 列表页面
     */
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/userMsg/list";
    }

    /**
     * 列表数据
     */
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysUserMsg> dataList(SysUserMsg sysUserMsg){
        sysUserMsg.setUserId(UserUtils.currentUser().getId());
        PageHelper.startPage(sysUserMsg.getPageNum(), sysUserMsg.getPageSize());
        return sysUserMsgService.findPageList(sysUserMsg);
    }

    /**
     * 已阅
     */
    @RequestMapping(value = "/read")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> read(@RequestParam(value = "id") String id){
        List<String> msgList = new ArrayList<>();
        if(StringUtils.isNoneBlank(id)){
            //全部已读
            msgList.add(id);
        }else{
            SysUserMsg findMsg = new SysUserMsg();
            findMsg.setUserId(UserUtils.currentUser().getId());
            findMsg.setStatus(SysDictConstants.MSG_READ_STATUS.NO.getValue());
            sysUserMsgService.findList(findMsg).forEach((msg)->{
                msgList.add(msg.getId());
            });
        }
        sysUserMsgService.read(msgList);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}