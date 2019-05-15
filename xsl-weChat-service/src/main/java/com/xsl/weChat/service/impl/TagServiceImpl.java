package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.TagService;
import com.xsl.wechat.mapper.XslTagMapper;
import com.xsl.wechat.vo.TagVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/11 16:28
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private XslTagMapper xslTagMapper;

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public XslResult getTagList() {
        List<TagVo> tagVoList = xslTagMapper.getTagList();
        try {
            if(tagVoList==null&&tagVoList.size()==0){
                logger.info("tagVoList is {}",tagVoList.toString());
                return XslResult.build(400,"系统繁忙请稍后重试");
            }
            return XslResult.build(1,"正常",tagVoList);
        }catch (Exception e){
            return XslResult.build(-1,"服务器异常");
        }
    }
}
