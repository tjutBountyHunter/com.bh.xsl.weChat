package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTask;
import org.apache.ibatis.annotations.Param;

/**
 * 处理任务Dao
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/3 21:28
 */
public interface DealTaskMapper {

    /**
     * 更新任务状态
     * @param state
     * @param taskId
     * @return
     */
    int updateTaskState(@Param("state") Byte state, @Param("taskId") String taskId);

    /**
     * 查询任务状态
     * @param taskId
     * @return
     */
    XslTask getTaskState(String taskId);

    /**
     * 更新hunter_task关联表
     * @param state
     * @param taskId
     * @return
     */
    int updateHunterTask(@Param("state") Byte state, @Param("taskId") String taskId);

}
