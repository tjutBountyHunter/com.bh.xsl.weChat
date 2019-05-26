package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.FileOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/25 12:15
 */
@Controller
@RequestMapping("/xsl")
public class FileUploadController {

    @Autowired
    private FileOperateService fileOperateService;

    @RequestMapping("/fileupload")
    @ResponseBody
    public XslResult fileUpload(HttpServletRequest request){
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("file");
        XslResult xslResult = fileOperateService.fileUpload(file);
        return xslResult;
    }
}
