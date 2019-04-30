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


    private static String code = "023Fb9a32KyJ9W0EPS632PES932Fb9aB";

    @Test
    public void getWXOpenId(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=wxb7715d658f00b0e1&secret=d95be64495bf3a94114bae2841a54961&js_code=" + code +"&"+
                "grant_type=authorization_code";
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
        System.out.println(jsonObject);
        if(jsonObject!=null){
            System.out.println(jsonObject.get("openid"));
        }
    }

}
