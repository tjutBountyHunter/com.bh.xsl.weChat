package com.xsl.wechat.mapper;

import com.xsl.wechat.vo.TagVo;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/11 16:18
 */
public interface XslTagMapper {

    /**
     * 取到所有标签
     * @return
     */
    List<TagVo> getTagList();

}
