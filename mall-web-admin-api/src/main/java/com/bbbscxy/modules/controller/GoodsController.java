package com.bbbscxy.modules.controller;

import com.bbbscxy.modules.entity.MallGoods;
import com.bbbscxy.modules.service.MallGoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 Administrator
 * @时间 2020-08-20 17:56
 * @版本 1.0
 * @说明
 */
@RestController
public class GoodsController {

    @DubboReference(url = "dubbo://127.0.0.1:19001")
    private MallGoodsService mallGoodsService;

    @RequestMapping("goodsInfo/{id}")
    public MallGoods goodsInfo(@PathVariable("id") Long id){
        return mallGoodsService.get(id);
    }
}
