package com.bbbscxy.modules.api;

import com.bbbscxy.common.response.MallResponseEntity;
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
public class GoodsApiController {

    @DubboReference(url = "dubbo://127.0.0.1:19001")
    private MallGoodsService mallGoodsService;

    @RequestMapping("goodsInfo/{id}")
    public MallResponseEntity<MallGoods> goodsInfo(@PathVariable("id") Long id){
        return new MallResponseEntity<MallGoods>().success(mallGoodsService.get(id));
    }

    @RequestMapping("exceptionTest/{id}")
    public MallResponseEntity<MallGoods> exceptionTest(@PathVariable("id") Long id) {
        int i = 1/0;
        return null;
    }
}
