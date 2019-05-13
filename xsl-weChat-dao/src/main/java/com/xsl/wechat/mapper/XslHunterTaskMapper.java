package com.xsl.wechat.mapper;

import java.util.Map;


public interface XslHunterTaskMapper {
    /**
     * 更新hunter_task表
     * @param map
     */
    void updateHunterTask(Map map);

    /**
     * 通过任务id查询猎人id
     * @param taskId
     * @return
     */
    String getSendIdByTaskId(String taskId);
}
