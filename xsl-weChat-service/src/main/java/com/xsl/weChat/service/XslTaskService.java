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
}
