package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/10 19:44
 */
@Controller
@RestController
@RequestMapping("/xsl/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 学校类别
     *
     * @return
     */
    @RequestMapping("/schoolClasses")
    public XslResult schoolMessage() {
        XslResult xslResult = schoolService.schoolMessage();
        return xslResult;
    }

}
