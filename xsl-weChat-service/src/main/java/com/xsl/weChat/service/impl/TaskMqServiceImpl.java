package com.xsl.weChat.service.impl;

import com.google.gson.Gson;
import com.xsl.weChat.common.util.GsonSingle;
import com.xsl.weChat.service.TaskMqService;
import com.xsl.wechat.pojo.TaskEsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/26 17:53
 */
@Service
public class TaskMqServiceImpl implements TaskMqService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private Destination addTaskInfo;

    @Override
    public void addEsTask(TaskEsInfo taskEsInfo) {
        Gson gson = GsonSingle.getGson();
        jmsTemplate.send(addTaskInfo,(session -> session.createTextMessage(gson.toJson(taskEsInfo))));
    }
}
