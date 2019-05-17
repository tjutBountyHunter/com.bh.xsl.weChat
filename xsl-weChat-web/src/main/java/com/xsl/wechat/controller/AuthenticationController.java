package com.xsl.wechat.controller;

import com.xsl.weChat.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vo.ResBaseVo;

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
    public ResBaseVo userAcc(String personalMessage, @RequestParam(value = "phoneNumber") String phone){
        ResBaseVo resBaseVo = authenticationService.AuthenticationUser(personalMessage,phone);
        return resBaseVo;
    }

}
