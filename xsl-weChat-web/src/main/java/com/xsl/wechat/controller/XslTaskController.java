package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/18 20:30
 */
@RequestMapping("/xslTask")
@Controller
public class XslTaskController {

    @Autowired
    private XslTaskService xslTaskService;

    private static Logger logger = LoggerFactory.getLogger(XslTaskController.class);

    /**
     * 分页查询任务列表
     * @param pageNum
     * @param PageSize
     * @return
     */
    @RequestMapping(value = "/getXslList",method = RequestMethod.GET)
    @ResponseBody
    public XslResult getXslTaskList(@RequestParam(value = "page",defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "perpage",defaultValue = "10") Integer PageSize){
        try {
            return xslTaskService.getTaskList(pageNum,PageSize);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
            return XslResult.build(-1,"服务器异常");
    }

    /**
     * 发布任务
     * @param issueData
     * @return
     */
    @RequestMapping(value = "/issueTask",method = RequestMethod.POST)
    @ResponseBody
    public XslResult issueTask(String issueData){
        XslResult xslResult = xslTaskService.issueTask(issueData);
        return xslResult;
    }

    /**
     * 获得自己所发布的任务
     * @param sendId
     * @return
     */
    @RequestMapping("/myIssueTask")
    @ResponseBody
    public XslResult getMyIssueTask(@RequestParam(value = "userId") String sendId){
        XslResult xslResult = xslTaskService.getMyIssueTask(sendId);
        return xslResult;
    }

    /**
     * 获取自己接受的任务
     * @param userId
     * @return
     */
    @RequestMapping("/myAcceptTask")
    @ResponseBody
    public XslResult getMyAcceptTask(String userId){
        XslResult xslResult = xslTaskService.getMyAcceptTask(userId);
        return xslResult;
    }

    /**
     * 获取任务详情
     * @param taskId
     * @return
     */
    @RequestMapping("/getTaskDetail")
    @ResponseBody
    public XslResult getXslTaskDetail(@RequestParam(value = "id") String taskId){
        XslResult xslResult = xslTaskService.getTaskDetail(taskId);
        return xslResult;
    }
}
