package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/11 17:15
 */
@Controller
@RestController
@RequestMapping("/xsl/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("list")
    public XslResult getTagList(){
        XslResult xslResult = tagService.getTagList();
        return xslResult;
    }

}
