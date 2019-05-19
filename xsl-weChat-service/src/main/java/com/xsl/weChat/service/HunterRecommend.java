package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/19 20:40
 */
public interface HunterRecommend {

    XslResult matchingHunter(String taskId);

}
