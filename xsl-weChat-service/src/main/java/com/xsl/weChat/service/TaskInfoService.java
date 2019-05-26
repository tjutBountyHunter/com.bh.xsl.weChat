package com.xsl.weChat.service;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/25 15:54
 */
public interface TaskInfoService {

    /**
     * 通过任务id获取任务图片
     * @param taskId
     * @return
     */
    List<String> getTaskImages(String taskId);

}
