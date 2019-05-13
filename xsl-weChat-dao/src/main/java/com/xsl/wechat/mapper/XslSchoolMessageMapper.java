package com.xsl.wechat.mapper;

import com.xsl.wechat.pojo.XslSchool;

import java.util.List;

/**
 * @author hlh
 */
public interface XslSchoolMessageMapper {

    List<String> selectByXslSchool();

    int selectBySchoolName(String schoolName);

    List<XslSchool> selectSchoolList();
}
