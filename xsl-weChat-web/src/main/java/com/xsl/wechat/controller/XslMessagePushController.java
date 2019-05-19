package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslMessagePushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 19:45
 */
@Controller
@RequestMapping("/xsl/push")
public class XslMessagePushController {

    @Autowired
    private XslMessagePushService xslMessagePushService;

    @RequestMapping("/hunterMessage")
    @ResponseBody
    public XslResult pushMessageToHunter(String template,String taskId){
        XslResult xslResult = xslMessagePushService.pushMessageToHunter(template,taskId);
        return xslResult;
    }

    @RequestMapping("/masterMessage")
    @ResponseBody
    public XslResult pushMessageToMaster(String template){
        XslResult xslResult = xslMessagePushService.pushMessageToMaster(template);
        return xslResult;
    }
}
