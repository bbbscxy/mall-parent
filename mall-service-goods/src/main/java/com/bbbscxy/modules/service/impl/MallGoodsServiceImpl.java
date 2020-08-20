package com.bbbscxy.modules.service.impl;

import com.bbbscxy.moduels.service.BaseServiceImpl;
import com.bbbscxy.modules.entity.MallGoods;
import com.bbbscxy.modules.mapper.MallGoodsMapper;
import com.bbbscxy.modules.service.MallGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @作者 Administrator
 * @时间 2020-08-20 15:49
 * @版本 1.0
 * @说明 商品业务层实现类
 */
@Slf4j
@DubboService
public class MallGoodsServiceImpl extends BaseServiceImpl<MallGoodsMapper, MallGoods> implements MallGoodsService {

}
