package com.xsl.wechat.mapper;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/19 21:24
 */
public interface XslHunterTagMapper {

    /**
     * 通过标签id获取猎人id
     * @param tagId
     * @return
     */
    List<String> getHunterIdsByTagId(String tagId);

    List<String> getHunterByTagIds(List<String> tagIds);

}
