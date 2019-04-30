package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslGetWeChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/29 10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class XslGetWeChatServiceImplTest {

    @Autowired
    private XslGetWeChatService xslGetWeChatService;

    @Test
    @Transactional
    public void getOpenIdAndSessionKey(){
        String code = "033JdqfK0Vl4g82TbTeK0VX7fK0Jdqfl";
        XslResult xslResult = xslGetWeChatService.getOpenIdAndSessionKey(code,
                "宋知勋","http://47.93.200.190/images/20181028003801.png");
//        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

}
