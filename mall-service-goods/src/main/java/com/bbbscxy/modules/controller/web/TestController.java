package com.bbbscxy.modules.controller.web;

import com.bbbscxy.modules.entity.MallGoods;
import com.bbbscxy.modules.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 Administrator
 * @时间 2020-08-20 16:54
 * @版本 1.0
 * @说明
 */
@RestController
public class TestController {

    @Autowired
    private MallGoodsService mallGoodsService;

    @RequestMapping("/get/{id}")
    public MallGoods get(@PathVariable("id") Long id){
        return mallGoodsService.get(id);
    }
}
