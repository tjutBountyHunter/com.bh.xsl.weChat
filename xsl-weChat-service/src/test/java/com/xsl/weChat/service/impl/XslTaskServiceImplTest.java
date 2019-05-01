package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.DateUtil;
import com.xsl.wechat.vo.XslTaskReqVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 22:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class XslTaskServiceImplTest {

    @Autowired
    private XslTaskServiceImpl xslTaskService;

    @Test
    public void getXslList(){
        XslResult xslResult = xslTaskService.getTaskList(1,10);
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    @Transactional
    public void issueTask() {
        XslTaskReqVo xslTaskVo = new XslTaskReqVo();
        xslTaskVo.setUserId("115eb729-500d-47f0-99e5-2325518d0cee");
        xslTaskVo.setTitle("快递悬赏服务");
        xslTaskVo.setType("服务");
        xslTaskVo.setMoney("5");
        xslTaskVo.setAddress("天津理⼯⼤学");
        xslTaskVo.setIssueTime(DateUtil.dateToString(new Date()));
        xslTaskVo.setMissionDetail("这是一个好任务");
        xslTaskVo.setAddressNoun("北区29号楼");
        XslResult xslResult = xslTaskService.issueTask(xslTaskVo);
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    public void getMyIssueTaskTest(){
        XslResult xslResult = xslTaskService.getMyIssueTask("oVLHi5Aw6E7Z6r9_gYikQDkJDBBQ");
//        List<XslTaskVo> xslTaskVoList =  xslResult.getData();
        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    public void getXslTaskMsg(){
        XslResult xslResult = xslTaskService.getTaskList(1,10);
//        List<XslTaskVo> xslTaskVoList = (List<XslTaskVo>) xslResult.getData();
//        Assert.assertNotEquals(0,xslTaskVoList.size());
    }
}
