package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.enums.UserStateEnum;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.AuthenticationService;
import com.xsl.wechat.mapper.AuthenticationMapper;
import com.xsl.wechat.mapper.XslUserMapper;
import com.xsl.wechat.pojo.XslUser;
import com.xsl.wechat.pojo.XslUserExample;
import com.xsl.wechat.vo.AuthenticationReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户认证
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:36
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Autowired
    private XslUserMapper xslUserMapper;

    @Override
    public XslResult AuthenticationUser(AuthenticationReqVo authenticationReqVo) {
        if(authenticationReqVo==null){
            return XslResult.build(-1,"认证信息不能为空");
        }
        try {
            XslUserExample example = new XslUserExample();
            XslUserExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(authenticationReqVo.getUserId());
            List<XslUser> list = xslUserMapper.selectByExample(example);
            if(list==null||list.size()==0){
                return XslResult.build(-1,"没有该用户");
            }
            LOGGER.info("code:"+UserStateEnum.CHECK_PENDING.getCode());
            LOGGER.info("state:" + list.get(0).getState().equals(UserStateEnum.CHECK_PENDING.getCode()));
            if(!list.get(0).getState().equals(UserStateEnum.CHECK_PENDING.getCode())){
                return XslResult.build(-1,"您以认证");
            }
            authenticationMapper.AuthenticationUser(authenticationReqVo);
            return XslResult.build(1,"认证成功");

        }catch (Exception e){
            LOGGER.error("服务器异常警告:"+e.getMessage());
            e.printStackTrace();
            return XslResult.build(500,"服务器异常");
        }

    }
}
