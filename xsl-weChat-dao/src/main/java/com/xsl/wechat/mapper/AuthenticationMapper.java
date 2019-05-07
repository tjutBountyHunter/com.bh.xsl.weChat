package com.xsl.wechat.mapper;

import com.xsl.wechat.vo.AuthenticationReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:42
 */
public interface AuthenticationMapper {

    /**
     * 更新用户信息
     * @param authenticationReqVo
     * @return
     */
    int AuthenticationUser(AuthenticationReqVo authenticationReqVo);

}
