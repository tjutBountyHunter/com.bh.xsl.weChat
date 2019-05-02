package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslHunterTask;
import com.xsl.wechat.pojo.XslTaskArea;

import java.util.List;

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


    /**
     * 通过任务Id来查询任务地区列表
     * @param xslHunterTasks
     * @return
     */
    List<XslTaskArea> getAcceptXslTaskArea(List<XslHunterTask> xslHunterTasks);

}
