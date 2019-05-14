package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTaskFile;

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

}
