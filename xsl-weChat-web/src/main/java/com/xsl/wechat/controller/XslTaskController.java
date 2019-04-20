package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/getXslList")
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
}
