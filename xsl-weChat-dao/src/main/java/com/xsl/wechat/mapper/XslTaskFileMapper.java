package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTaskFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/14 10:38
 */
public interface XslTaskFileMapper {

    /**
     * 更新任务文件关联表
     * @param xslTaskFile
     * @return
     */
    int insetTaskFile(XslTaskFile xslTaskFile);

    /**
     * 查询任务图片
     * @param taskId
     * @param type
     * @return
     */
    List<String> selectTaskImage(@Param("taskId") String taskId,@Param("type") String type);

}
