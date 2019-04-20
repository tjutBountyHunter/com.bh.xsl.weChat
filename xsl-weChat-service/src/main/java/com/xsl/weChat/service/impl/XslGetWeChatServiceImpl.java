package com.xsl.weChat.service.impl;

import com.xsl.weChat.service.XslGetWeChatService;
import com.xsl.wechat.vo.WeChatAccountConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 此功能先不开发
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 17:10
 */
@Service
public class XslGetWeChatServiceImpl implements XslGetWeChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XslGetWeChatServiceImpl.class);

    public WeChatAccountConfig getOpenIdAndSessionKey(String code, String nickName, String avatarUrl) {

        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=APPID&" +
                "secret=SECRET&l&js_code="+code+"&" +
                "grant_type=authorization_code";
        return null;
    }

}
