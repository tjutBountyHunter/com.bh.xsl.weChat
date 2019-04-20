package com.xsl.weChat.service;

import com.xsl.wechat.vo.WeChatAccountConfig;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 17:04
 */
public interface XslGetWeChatService {

    /**
     * 获取微信的openid和session_key
     * @param code
     * @param nickName
     * @param avatarUrl
     * @return
     */
    WeChatAccountConfig getOpenIdAndSessionKey(String code,String nickName,String avatarUrl);

}
