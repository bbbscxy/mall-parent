package com.bbbscxy.modules.service.impl;

import com.bbbscxy.common.service.BaseServiceImpl;
import com.bbbscxy.modules.entity.MallMember;
import com.bbbscxy.modules.mapper.MallMemberMapper;
import com.bbbscxy.modules.service.MallMemberService;
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
public class MallMemberServiceImpl extends BaseServiceImpl<MallMemberMapper, MallMember> implements MallMemberService {

}
