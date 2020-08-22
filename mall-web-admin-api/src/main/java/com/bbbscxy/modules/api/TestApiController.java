package com.bbbscxy.modules.api;

import com.bbbscxy.common.response.MallResponseEntity;
import com.bbbscxy.modules.entity.MallGoods;
import com.bbbscxy.modules.entity.MallMember;
import com.bbbscxy.modules.entity.MallOrder;
import com.bbbscxy.modules.entity.MallStore;
import com.bbbscxy.modules.service.MallGoodsService;
import com.bbbscxy.modules.service.MallMemberService;
import com.bbbscxy.modules.service.MallOrderService;
import com.bbbscxy.modules.service.MallStoreService;
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
public class TestApiController {

    @DubboReference
    private MallGoodsService mallGoodsService;
    /*@DubboReference(url = "${dubbo.order.reference.url}")
    private MallOrderService mallOrderService;
    @DubboReference(url = "${dubbo.store.reference.url}")
    private MallStoreService mallStoreService;
    @DubboReference(url = "${dubbo.member.reference.url}")
    private MallMemberService mallMemberService;*/

    @RequestMapping("goods/{id}")
    public MallResponseEntity<MallGoods> testGoods(@PathVariable("id") Long id){
        return new MallResponseEntity<MallGoods>().success(mallGoodsService.get(id));
    }

    /*@RequestMapping("order/{id}")
    public MallResponseEntity<MallOrder> testOrder(@PathVariable("id") Long id){
        return new MallResponseEntity<MallOrder>().success(mallOrderService.get(id));
    }

    @RequestMapping("store/{id}")
    public MallResponseEntity<MallStore> testStore(@PathVariable("id") Long id){
        return new MallResponseEntity<MallStore>().success(mallStoreService.get(id));
    }

    @RequestMapping("member/{id}")
    public MallResponseEntity<MallMember> testMember(@PathVariable("id") Long id){
        return new MallResponseEntity<MallMember>().success(mallMemberService.get(id));
    }*/
}
