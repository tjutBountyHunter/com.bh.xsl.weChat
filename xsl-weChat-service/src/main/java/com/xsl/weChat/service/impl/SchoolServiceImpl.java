package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.JedisClientUtil;
import com.xsl.weChat.common.util.JsonUtils;
import com.xsl.weChat.service.SchoolService;
import com.xsl.wechat.mapper.XslSchoolMessageMapper;
import com.xsl.wechat.pojo.XslSchool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vo.SchoolReqVo;
import vo.SchoolResVo;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/10 19:06
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private XslSchoolMessageMapper xslSchoolMessageMapper;

    @Value("${REDIS_SCHOOL_LIST}")
    private String REDIS_SCHOOL_LIST;
    @Value("${REDIS_SCHOOL_LIST_VERSION}")
    private String REDIS_SCHOOL_LIST_VERSION;

    private static final Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

    public XslResult schoolMessage() {
        String version = "1";
        SchoolResVo schoolResVo = new SchoolResVo();
        String versionRedis = JedisClientUtil.get(REDIS_SCHOOL_LIST_VERSION);
        schoolResVo.setVersion(version);
        if(!StringUtils.isEmpty(versionRedis)){
            version = versionRedis;
            schoolResVo.setVersion(version);
        }

        String infoByCache = getInfoByCache(1, REDIS_SCHOOL_LIST, null, version);
        if(!StringUtils.isEmpty(infoByCache)){
            schoolResVo.setSchools(infoByCache);
            return XslResult.ok(schoolResVo);
        }

        List<XslSchool> xslSchools = xslSchoolMessageMapper.selectSchoolList();
        if(xslSchools == null || xslSchools.size() < 1){
            return XslResult.ok(schoolResVo);
        }

        String schoolList = JsonUtils.objectToJson(xslSchools);
        JedisClientUtil.set(REDIS_SCHOOL_LIST +"_"+ version, schoolList);
        JedisClientUtil.set(REDIS_SCHOOL_LIST_VERSION, version);
        logger.info("SchoolService.schoolMessage req:{}",schoolList);

        schoolResVo.setVersion(version);
        schoolResVo.setSchools(schoolList);


        return XslResult.ok(schoolResVo);
    }

    private String getInfoByCache(int type, String key, SchoolReqVo schoolReqVo, String version){
        String result = "";
        if(type == 1){
            result = JedisClientUtil.get(key +"_"+ version);
        }

        if(type == 2){
            Integer schoolid = schoolReqVo.getSchoolid();
            result = JedisClientUtil.get(key +"_"+ schoolid+"_"+version);
        }

        if(type == 3){
            Integer collegeId = schoolReqVo.getCollegeid();
            result = JedisClientUtil.get(key +"_"+ collegeId+"_"+version);
        }
        return result;
    }
}
