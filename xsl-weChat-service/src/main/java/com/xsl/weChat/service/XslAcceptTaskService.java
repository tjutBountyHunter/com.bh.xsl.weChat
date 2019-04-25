package com.xsl.weChat.service;


import com.xsl.weChat.common.pojo.XslResult;



public interface XslAcceptTaskService {
    XslResult AcceptTask(String userId, String taskId);

}
