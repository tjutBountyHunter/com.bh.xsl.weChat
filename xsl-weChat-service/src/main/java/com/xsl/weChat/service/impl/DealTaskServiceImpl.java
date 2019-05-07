package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.enums.TaskStateEnum;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.DealTaskService;
import com.xsl.wechat.mapper.DealTaskMapper;
import com.xsl.wechat.pojo.XslTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/3 21:38
 */
@Service
public class DealTaskServiceImpl implements DealTaskService {

    @Autowired
    private DealTaskMapper dealTaskMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(DealTaskServiceImpl.class);

    public XslResult deleteTask(String taskId) {
        try {
            //非空判断
            if(StringUtils.isEmpty(taskId)){
                return XslResult.build(400,"参数错误");
            }
            //进行状态判断
            XslTask xslTask = dealTaskMapper.getTaskState(taskId);
            if(!xslTask.getState().equals(TaskStateEnum.TASK_WAIT.getState())){
                LOGGER.error("【删除任务】任务状态不正确,任务状态为{}",xslTask.getState());
                return XslResult.build(-1,"该状态下不允许被删除");
            }
            int updateResult = dealTaskMapper.updateTaskState(TaskStateEnum.TASK_TO_WITHDRAW.getState(),taskId);
            if(updateResult < 1){
                LOGGER.error("【删除任务】任务删除失败,updateResult={}",updateResult);
                return XslResult.build(-1,"删除任务失败");
            }
            return XslResult.build(1,"撤回成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return XslResult.build(500,"服务器异常");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public XslResult completeTask(String taskId) {
        try {
            if(StringUtils.isEmpty(taskId)){
                return XslResult.build(400,"参数错误");
            }
            //进行状态判断
            XslTask xslTask = dealTaskMapper.getTaskState(taskId);
            if(!xslTask.getState().equals(TaskStateEnum.TASK_ONGOING.getState())){
                LOGGER.error("【完成任务】任务状态不正确,任务状态为{}",xslTask.getState());
                return XslResult.build(-1,"该状态下不允许被完成");
            }
            int updateResult = dealTaskMapper.updateTaskState(TaskStateEnum.TASK_FINISH.getState(),taskId);
            if(updateResult < 1){
                LOGGER.error("【完成任务】任务删除失败(更新task表失败),updateResult={}",updateResult);
                throw new RuntimeException("完成任务失败");
            }
            int result = dealTaskMapper.updateHunterTask(TaskStateEnum.TASK_FINISH.getState(),taskId);
            if(result < 1){
                LOGGER.error("【完成任务】任务删除失败:更新hunter_task表失败,任务状态为{}",xslTask.getState());
                throw new RuntimeException("完成任务失败");
            }
            return XslResult.build(1,"恭喜您完成任务");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return XslResult.build(500,"服务器异常");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public XslResult cancelAcceptTask(String taskId) {
        try {
            if(StringUtils.isEmpty(taskId)){
                return XslResult.build(400,"参数错误");
            }
            //进行状态判断
            XslTask xslTask = dealTaskMapper.getTaskState(taskId);
            if(!xslTask.getState().equals(TaskStateEnum.TASK_ONGOING.getState())){
                LOGGER.error("【取消已接受任务】任务状态不正确,任务状态为{}",xslTask.getState());
                return XslResult.build(-1,"该状态下不允许被取消");
            }
            int updateResult = dealTaskMapper.updateTaskState(TaskStateEnum.TASK_WAIT.getState(),taskId);
            if(updateResult < 1){
                LOGGER.error("【取消已接受任务】取消接受失败(更新task表失败),updateResult={}",updateResult);
                throw new RuntimeException("完成任务失败");
            }
            int result = dealTaskMapper.updateHunterTask(TaskStateEnum.TASK_WAIT.getState(),taskId);
            if(result < 1){
                LOGGER.error("【取消已接任务】取消接受失败:更新hunter_task表失败,任务状态为{}",xslTask.getState());
                throw new RuntimeException("完成任务失败");
            }
            return XslResult.build(1,"取消已接任务成功");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return XslResult.build(500,"服务器异常");
        }
    }
}
