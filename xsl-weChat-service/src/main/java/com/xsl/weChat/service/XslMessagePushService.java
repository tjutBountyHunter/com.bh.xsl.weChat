package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 0:19
 */
public interface XslMessagePushService {

    /**
     * 给猎人发送推送
     * @param wxPushTemplate
     * @return
     */
    XslResult pushMessageToHunter(String wxPushTemplate,String taskId);


    /**
     * 给雇主发送推送
     * @param wxPushTemplate
     * @return
     */
    XslResult pushMessageToMaster(String wxPushTemplate);

}
