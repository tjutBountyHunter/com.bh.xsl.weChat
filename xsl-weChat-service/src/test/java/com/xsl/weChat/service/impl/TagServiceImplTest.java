package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/11 17:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @Test
    public void getTagList() {
        XslResult xslResult = tagService.getTagList();
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }
}