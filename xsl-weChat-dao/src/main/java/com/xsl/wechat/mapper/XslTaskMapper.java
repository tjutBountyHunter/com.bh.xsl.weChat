package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslHunterTask;
import com.xsl.wechat.pojo.XslTask;

import java.util.List;
import java.util.Map;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/17 21:12
 */

public interface XslTaskMapper {

    /**
     * 分页返回任务列表
     * @param map
     * @return
     */
    List<XslTask> getTaskList(Map map);

    /**
     * 返回任务数量
     * @return
     */
    int getXslTaskTotalCount();

    /**
     * 通过类别id查看类别名
     * @param cid
     * @return
     */
    String getCategoryNameByCid(Integer cid);

    /**
     * 通过发送者id来查找雇主姓名
     * @param sendId
     * @return
     */
    String getUerNameBySendId(String sendId);

    /**
     * 通过发送者id来查找雇主头像
     * @param sendId
     * @return
     */
    String getImageBySendId(String sendId);

    /**
     * 插入任务信息
     * @param xslTask
     * @return
     */
    int insertTask(XslTask xslTask);

    /**
     * 添加类别任务数量
     * @param name
     */
    void increaseTaskNum(String name);

    /**
     * 通过类别名取到类别Id
     * @param name
     * @return
     */
    Integer getCidByTypeName(String name);

    /**
     * 获取我发布过的任务
     * @param sendId
     * @return
     */
    List<XslTask> getMyTaskList(String sendId);

    /**
     * 通过masterId来查询userId
     * @param userId
     * @return
     */
    String getMasterByOpenId(String userId);

    /**
     * 通过userId来查询雇主Id
     * @param userId
     * @return
     */
    String getMasterIdByOpenId(String userId);

    /**
     * 通过openid来获取猎人id
     * @param userId
     * @return
     */
    String getHunterIdByOpenId(String userId);

    /**
     * 通过hunterId来获取我接受过的任务
     * @param hunterId
     * @return
     */
    List<XslHunterTask> getMyAcceptTask(String hunterId);

    /**
     * 通过任务id来查询任务列表
     * @param xslHunterTasks
     * @return
     */
    List<XslTask> getAcceptXslTask(List<XslHunterTask> xslHunterTasks);

    /**
     * 通过任务id查询任务
     * @param taskId
     * @return
     */
    XslTask getTaskByTasKId(String taskId);

}
