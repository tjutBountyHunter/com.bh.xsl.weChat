package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTask;
import com.xsl.wechat.vo.XslTaskVo;

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
    String getUerNameBySendId(Integer sendId);

    /**
     * 通过发送者id来查找雇主头像
     * @param sendId
     * @return
     */
    String getImageBySendId(Integer sendId);

    /**
     * 插入任务信息
     * @param xslTaskVo
     * @return
     */
    int insertTask(XslTaskVo xslTaskVo);
}
