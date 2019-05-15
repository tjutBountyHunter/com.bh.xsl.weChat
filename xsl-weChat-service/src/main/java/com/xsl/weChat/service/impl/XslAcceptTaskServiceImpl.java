package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslAcceptTaskService;
import com.xsl.wechat.mapper.XslHunterTaskMapper;
import com.xsl.wechat.mapper.XslTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hqh
 */
@Service
public class XslAcceptTaskServiceImpl implements XslAcceptTaskService {

    public static final String taskState = "2";

    @Autowired
    private XslTaskMapper TaskDao;
    @Autowired
    private XslHunterTaskMapper HunterTaskDao;
    private static final Logger logger = LoggerFactory.getLogger(XslAcceptTaskServiceImpl.class);

    @Override
    public XslResult AcceptTask(String userId, String taskId) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("hunterId", TaskDao.getHunterIdByOpenId(userId));
        map.put("taskId", taskId);
        map.put("taskState", taskState);
        try {
            HunterTaskDao.updateHunterTask(map);
            TaskDao.alterTaskState(map);
            return XslResult.build(1, "接受成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return XslResult.build(-1, "服务器异常");
    }
}
