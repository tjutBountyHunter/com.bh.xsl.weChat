package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/18 19:51
 */
public interface XslTaskService {

    /**
     * 分页查询任务列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    XslResult getTaskList(Integer pageNum, Integer pageSize);

    /**
     * 发布任务
     * @param issueData
     * @return
     */
    XslResult issueTask(String issueData);

    /**
     * 得到我发送过的任务
     * @param sendId
     * @return
     */
    XslResult getMyIssueTask(String sendId);

    /**
     * 得到我接受过的任务
     * @param userId
     * @return
     */
    XslResult getMyAcceptTask(String userId);

    /**
     * 获取任务详情
     * @param taskId
     * @return
     */
    XslResult getTaskDetail(String taskId);
}
