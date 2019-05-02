package com.xsl.weChat.service;


import com.xsl.weChat.common.pojo.XslResult;

public interface XslAcceptTaskService {

    /**
     * 接收任务
     * @param userId
     * @param taskId
     * @return
     */
    XslResult AcceptTask(String userId, String taskId);
}
