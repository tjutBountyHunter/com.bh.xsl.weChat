package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslTaskTag;
import com.xsl.wechat.pojo.XslTaskTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 0:59
 */
public interface XslTaskTagMapper {

    int countByExample(XslTaskTagExample example);

    int deleteByExample(XslTaskTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslTaskTag record);

    int insertSelective(XslTaskTag record);

    List<XslTaskTag> selectByExample(XslTaskTagExample example);

    XslTaskTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslTaskTag record, @Param("example") XslTaskTagExample example);

    int updateByExample(@Param("record") XslTaskTag record, @Param("example") XslTaskTagExample example);

    int updateByPrimaryKeySelective(XslTaskTag record);

    int updateByPrimaryKey(XslTaskTag record);

    int insertSelectiveBatch(List<XslTaskTag> list);

    List<String> selectTagidByExample(XslTaskTagExample example);

    //    重写
    List<XslTaskTag> getTagsByTaskId(String taskId);

    List<XslTaskTag> getTasksByTagId(String taskTag);

    List<String> selectTagIdByExample(XslTaskTagExample example);

    List<String> getTagIdsByTaskId(String taskId);

}
