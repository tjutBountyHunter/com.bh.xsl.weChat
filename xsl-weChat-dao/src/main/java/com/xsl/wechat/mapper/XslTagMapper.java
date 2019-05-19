package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTag;
import com.xsl.wechat.pojo.XslTagExample;
import com.xsl.wechat.vo.TagVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 1:01
 */
public interface XslTagMapper {
    int countByExample(XslTagExample example);

    int deleteByExample(XslTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTag record);

    int insertSelective(XslTag record);

    List<XslTag> selectByExample(XslTagExample example);

    List<XslTag> selectByExampleLimit(@Param("example")XslTagExample example, @Param("limit")Integer limit);

    XslTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTag record, @Param("example") XslTagExample example);

    int updateByExample(@Param("record") XslTag record, @Param("example") XslTagExample example);

    int updateByPrimaryKeySelective(XslTag record);

    int updateByPrimaryKey(XslTag record);

    int updateUseNumByExample(XslTagExample example);

    List<TagVo> getTagList();

    String getTagNameByTagId(String tagId);

}
