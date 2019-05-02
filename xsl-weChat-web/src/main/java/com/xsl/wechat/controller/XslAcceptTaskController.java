package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xsl.weChat.service.XslAcceptTaskService;

@Controller
@RequestMapping("/xslTask")
public class XslAcceptTaskController {
    @Autowired
    XslAcceptTaskService xslAcceptTaskService;
    private static Logger logger = LoggerFactory.getLogger(XslAcceptTaskController.class);

    @RequestMapping("/acceptTask")
    @ResponseBody
    public XslResult  acceptTask(@RequestParam(value = "userid")String userId,@RequestParam(value = "id")String taskId){
        try {
            if(userId.equals("")||taskId.equals("")||userId==null||taskId==null){
                return XslResult.build(-1,"输入不正确");
            }
            return xslAcceptTaskService.AcceptTask(userId,taskId);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e);
        }
        return XslResult.build(-1,"服务器异常");


    }

}

