package com.bbbscxy.modules.service.impl;

import com.bbbscxy.common.service.BaseServiceImpl;
import com.bbbscxy.modules.entity.MallOrder;
import com.bbbscxy.modules.mapper.MallOrderMapper;
import com.bbbscxy.modules.service.MallOrderService;
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
public class MallOrderServiceImpl extends BaseServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {

}
