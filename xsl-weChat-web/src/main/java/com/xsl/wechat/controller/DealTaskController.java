package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.DealTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/4 12:43
 */
@Controller
@RestController
@RequestMapping("/dealTask")
public class DealTaskController {

    @Autowired
    private DealTaskService dealTaskService;

    @RequestMapping("/deleteTask")
    public XslResult deleteTask(String taskId){
        XslResult xslResult = dealTaskService.deleteTask(taskId);
        return xslResult;
    }

    @RequestMapping("/completeTask")
    public XslResult completeTask(String taskId){
        XslResult xslResult = dealTaskService.completeTask(taskId);
        return xslResult;
    }

    @RequestMapping("/cancelTask")
    public XslResult cancelTask(String taskId){
        XslResult xslResult = dealTaskService.cancelAcceptTask(taskId);
        return xslResult;
    }

}
