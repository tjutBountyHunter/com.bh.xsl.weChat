package com.xsl.weChat.service.impl;

import com.xsl.weChat.service.TaskInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/25 17:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class TaskInfoServiceImplTest {

    @Autowired
    private TaskInfoService taskInfoService;

    @Test
    public void getTaskImages() {
        List<String> list = taskInfoService.getTaskImages("69879e86dd5b4cb5b43a7bb105184993");
        Assert.assertNotEquals(0,list.size());
    }
}