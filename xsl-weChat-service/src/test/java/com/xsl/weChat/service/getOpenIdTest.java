package com.xsl.weChat.service;

import com.xsl.weChat.common.util.CommonUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 19:48
 */
public class getOpenIdTest {


    private static String code = "023sqyAj2WxJBD0J0vzj2fHMAj2sqyA0";


    @Test
    public void getWXOpenId(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=APPID&secret=SECRET&js_code=" + code +"&"+
                "grant_type=authorization_code";
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
        System.out.println(jsonObject);
        if(jsonObject!=null){
            System.out.println(jsonObject.get("openid"));
        }
    }

}
