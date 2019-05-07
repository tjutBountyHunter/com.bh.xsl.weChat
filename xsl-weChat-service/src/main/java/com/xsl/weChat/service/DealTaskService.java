package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;

/**
 * 处理任务
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/3 21:17
 */
public interface DealTaskService {

    /**
     * 删除（撤回）任务
     * @param taskId
     * @return
     */
    XslResult deleteTask(String taskId);

    /**
     * 完成任务
     * @param taskId
     * @return
     */
    XslResult completeTask(String taskId);

    /**
     * 取消我接受的任务接口
     * @return
     */
    XslResult cancelAcceptTask(String task);
}
