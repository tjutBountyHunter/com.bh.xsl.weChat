package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.DealTaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/3 22:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class DealTaskServiceImplTest {

    @Autowired
    private DealTaskService dealTaskService;

    @Test
    @Transactional
    public void deleteTask() {
        XslResult xslResult = dealTaskService.deleteTask("56a357adc2174efd8c24d4e76b0b2416");
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    public void completeTask() {
        XslResult xslResult = dealTaskService.completeTask("eb9b0e0999ad45e79acd043ec4b25a29");
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    public void cancelAcceptTask() {
        XslResult xslResult = dealTaskService.cancelAcceptTask("eb9b0e0999ad45e79acd043ec4b25a29");
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }
}