package com.xsl.weChat.service.impl;

import com.xsl.user.SupplementUserInfoResource;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.weChat.common.util.JsonUtils;
import com.xsl.weChat.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 用户认证
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:36
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Resource
    private SupplementUserInfoResource supplementUserInfoResource;

    @Override
    public ResBaseVo AuthenticationUser(String personalMessage){
        try {
            UserAccReqVo userAccReqVo = JsonUtils.jsonToPojo(personalMessage, UserAccReqVo.class);
            if (StringUtils.isEmpty(personalMessage)){
                return com.xsl.user.vo.ResBaseVo.build(-1,"数据不能为空");
            }
            userAccReqVo.setPhone(userAccReqVo.getPhone());
            userAccReqVo.setSource("weChat");
            ResBaseVo resBaseVo = supplementUserInfoResource.userAcc(userAccReqVo);
            return resBaseVo;
        }catch (Exception e){
            LOGGER.error("服务器异常:"+e.getMessage());
            return ResBaseVo.build(500,"服务器异常");
        }
    }

}
