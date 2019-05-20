package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.enums.TaskStateEnum;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslAcceptTaskService;
import com.xsl.wechat.mapper.XslHunterTaskMapper;
import com.xsl.wechat.mapper.XslTaskMapper;
import com.xsl.wechat.mapper.XslUserMapper;
import com.xsl.wechat.pojo.XslTask;
import com.xsl.wechat.pojo.XslUser;
import com.xsl.wechat.pojo.XslUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private XslUserMapper xslUserMapper;

    @Autowired
    private XslTaskMapper xslTaskMapper;

    private static final Logger logger = LoggerFactory.getLogger(XslAcceptTaskServiceImpl.class);

    @Override
    public XslResult AcceptTask(String userId, String taskId) {
        XslTask xslTask = xslTaskMapper.getTaskByTasKId(taskId);
        XslUserExample xslUserExample = new XslUserExample();
        XslUserExample.Criteria criteria = xslUserExample.createCriteria();
        criteria.andMasteridEqualTo(xslTask.getSendId());
        List<XslUser> list = xslUserMapper.selectByExample(xslUserExample);
        if(list!=null&&list.size()<1){
            return XslResult.build(-1,"这个任务未发布");
        }

        if(userId.equals(list.get(0).getUserid())){
            return XslResult.build(-1,"该任务是由您发布的，您不能接收");
        }
        if(xslTask.getState().equals(TaskStateEnum.TASK_WAIT)||
                xslTask.getState().equals(TaskStateEnum.HUNTER_IN_DIRECTING)){
            return XslResult.build(-1,"任务已经被接收");
        }
        Map<String, String> map = new HashMap<>();
        map.put("hunterId", TaskDao.getHunterIdByOpenId(userId));
        map.put("taskId", taskId);
        map.put("taskState", taskState);
        try {
            HunterTaskDao.updateHunterTask(map);
            TaskDao.alterTaskState(map);
            return XslResult.build(1, "接受成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("服务器异常");
        }
    }
}
