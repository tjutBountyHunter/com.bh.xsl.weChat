package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.util.JsonUtils;
import com.xsl.weChat.service.XslMessagePushService;
import com.xsl.wechat.pojo.WxPushTemplateParam;
import com.xsl.wechat.vo.WxPushTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 18:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
public class XslMessagePushServiceImplTest {

    @Autowired
    private XslMessagePushService xslMessagePushService;

    @Test
    public void pushMessageToHunter() {
        WxPushTemplate wxPushTemplate = new WxPushTemplate();
        wxPushTemplate.setTouser("oVLHi5Aw6E7Z6r9_gYikQDkJDBBQ");
        wxPushTemplate.setTemplate_id("DwkNVMerE7uEbLhatz47QEQXRY8q795nm-6Lpc0dVms");
        wxPushTemplate.setForm_id("ff0dd61b00309352a6cb3ede89412d65");
        List<WxPushTemplateParam> list = new ArrayList<WxPushTemplateParam>(10);
//        list.add(new WxPushTemplateParam("keyword1","1261352175352"));
//        list.add(new WxPushTemplateParam("keyword2","咖啡"));
//        list.add(new WxPushTemplateParam("keyword3","天津市河西区"));
//        list.add(new WxPushTemplateParam("keyword4","680.00元"));
//        wxPushTemplate.setData(list);
        HashMap<String,WxPushTemplateParam> map = new HashMap<String, WxPushTemplateParam>();
        map.put("keyword4",new WxPushTemplateParam("680.00元"));
        map.put("keyword3",new WxPushTemplateParam("天津市河西区"));
        map.put("keyword2",new WxPushTemplateParam("咖啡"));
        map.put("keyword1",new WxPushTemplateParam("1261352175352"));
        System.out.println(JsonUtils.objectToJson(map));
        wxPushTemplate.setData(map);
//        XslResult xslResult = xslMessagePushService.pushMessageToHunter(wxPushTemplate);
//        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
    }

    @Test
    public void testJson(){
//        List<WxPushTemplateParam> list = new ArrayList<WxPushTemplateParam>(10);
//        list.add(new WxPushTemplateParam("keyword1","1261352175352"));
//        list.add(new WxPushTemplateParam("keyword2","咖啡"));
//        list.add(new WxPushTemplateParam("keyword3","天津市河西区"));
//        list.add(new WxPushTemplateParam("keyword4","680.00元"));
        HashMap<String,WxPushTemplateParam> map = new HashMap<String, WxPushTemplateParam>();
        map.put("keyword1",new WxPushTemplateParam("1261352175352"));
        map.put("keyword2",new WxPushTemplateParam("咖啡"));
        map.put("keyword3",new WxPushTemplateParam("天津市河西区"));
        map.put("keyword4",new WxPushTemplateParam("680.00元"));
        System.out.println(map.toString());
    }
}