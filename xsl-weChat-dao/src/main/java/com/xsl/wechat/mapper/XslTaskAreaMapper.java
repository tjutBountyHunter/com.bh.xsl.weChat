package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTaskArea;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/29 11:25
 */
public interface XslTaskAreaMapper {

    /**
     * 通过任务id查询任务区域信息
     * @param taskId
     * @return
     */
    XslTaskArea getTaskMsgByTaskId(String taskId);

    /**
     * 插入任务区域信息
     * @param xslTaskArea
     * @return
     */
    int insertTaskArea(XslTaskArea xslTaskArea);
}
