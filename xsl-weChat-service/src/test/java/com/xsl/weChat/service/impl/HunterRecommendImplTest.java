package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.HunterRecommend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/19 22:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class HunterRecommendImplTest {

    @Autowired
    private HunterRecommend hunterRecommend;

    @Test
    public void matchingHunter() {
        XslResult xslResult = hunterRecommend.matchingHunter("23c5006f92f04edfba11ceeb9b00cedf");
        if(xslResult.isOK()){
            System.out.println(xslResult.getData());
        }
        System.out.println(xslResult.getMsg());
    }
}