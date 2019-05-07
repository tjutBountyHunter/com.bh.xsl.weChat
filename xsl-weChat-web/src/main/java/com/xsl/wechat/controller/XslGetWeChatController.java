package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslGetWeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取微信openid和session_key controller层
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/29 1:07
 */
@Controller
@RestController
public class XslGetWeChatController {

    @Autowired
    private XslGetWeChatService xslGetWeChatService;

    @RequestMapping("/getOpenid")
    public XslResult getOpenIdAndSessionKey(String code, String nickName, String avatarUrl){
        XslResult xslResult = xslGetWeChatService.getOpenIdAndSessionKey(code,nickName,avatarUrl);
        return xslResult;
    }
}
