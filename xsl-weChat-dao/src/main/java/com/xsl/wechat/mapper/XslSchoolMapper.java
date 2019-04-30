package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslSchool;
import com.xsl.wechat.pojo.XslSchoolExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XslSchoolMapper {
    int countByExample(XslSchoolExample example);

    int deleteByExample(XslSchoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslSchool record);

    int insertSelective(XslSchool record);

    List<XslSchool> selectByExample(XslSchoolExample example);

    XslSchool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslSchool record, @Param("example") XslSchoolExample example);

    int updateByExample(@Param("record") XslSchool record, @Param("example") XslSchoolExample example);

    int updateByPrimaryKeySelective(XslSchool record);

    int updateByPrimaryKey(XslSchool record);
}