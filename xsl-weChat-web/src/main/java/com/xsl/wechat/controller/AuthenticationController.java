package com.xsl.wechat.controller;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.AuthenticationService;
import com.xsl.wechat.vo.AuthenticationReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 12:36
 */
@Controller
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/personalMessage")
    public XslResult authenticationUser(AuthenticationReqVo authenticationReqVo){
        XslResult xslResult = authenticationService.AuthenticationUser(authenticationReqVo);
        return xslResult;
    }

}
