package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.wechat.vo.XslTaskReqVo;

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
     * @param xslTaskReqVo
     * @return
     */
    XslResult issueTask(XslTaskReqVo xslTaskReqVo);

    /**
     * 得到我发送过的任务
     * @param sendId
     * @return
     */
    XslResult getMyIssueTask(String sendId);
}
