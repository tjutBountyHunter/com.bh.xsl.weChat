package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.wechat.vo.AuthenticationReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:30
 */
public interface AuthenticationService {

    XslResult AuthenticationUser(AuthenticationReqVo authenticationReqVo);

//    XslResult getSchoolInfo();

}
