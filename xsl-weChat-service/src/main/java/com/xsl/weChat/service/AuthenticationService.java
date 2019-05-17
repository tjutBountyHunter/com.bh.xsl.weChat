package com.xsl.weChat.service;

import vo.ResBaseVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:30
 */
public interface AuthenticationService {

//    XslResult AuthenticationUser(AuthenticationReqVo authenticationReqVo);

//    XslResult getSchoolInfo();

    ResBaseVo AuthenticationUser(String personalMessage, String phone);

//    ResBaseVo AuthenticationUser(UserAccReqVo userAccReqVo);
}
