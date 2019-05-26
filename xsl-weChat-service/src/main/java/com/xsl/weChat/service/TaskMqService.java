package com.xsl.weChat.service;

import com.xsl.wechat.pojo.TaskEsInfo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/26 17:44
 */
public interface TaskMqService {

    /**
     * 数据同步
     * @param taskEsInfo
     */
    void addEsTask(TaskEsInfo taskEsInfo);
}
