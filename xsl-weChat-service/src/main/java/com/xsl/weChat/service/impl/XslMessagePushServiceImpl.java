package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslMessagePushService;
import com.xsl.wechat.pojo.WxConfig;
import com.xsl.wechat.vo.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.xsl.weChat.common.util.CommonUtil.httpsRequest;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 0:21
 */
@Service
public class XslMessagePushServiceImpl implements XslMessagePushService {

    private static final Logger logger = LoggerFactory.getLogger(XslMessagePushServiceImpl.class);

    public XslResult pushMessageToHunter(String template) {

        try {
            AccessToken token = getAccessToken();
            if (token==null){
                return XslResult.build(400,"获取token失败");
            }
            boolean flag = sendTemplateMsg(token.getAccessToken(),template);
            if (flag){
                logger.info("推送成功");
                return XslResult.build(1,"推送成功");
            }
            return XslResult.build(400,"推送失败");
        }catch (Exception e){
            logger.error(e.getMessage());
            return XslResult.build(500,"服务器异常");
        }
    }

    private boolean sendTemplateMsg(String accessToken, String wxPushTemplate) {
        boolean flag = false;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = httpsRequest(requestUrl,"POST",wxPushTemplate);
        logger.info("wxPushTemplate is {}",wxPushTemplate);

        if (jsonObject != null) {
            Integer errorCode = jsonObject.getInt("errcode");
            String errorMessage = jsonObject.getString("errmsg");
            if (errorCode == 0) {
                flag = true;
            } else {
                logger.info("模板消息发送失败:" + errorCode + "," + errorMessage);
                flag = false;
            }
        }
        return flag;
    }

    private AccessToken getAccessToken(){
        AccessToken token = null;
        String tokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                "&appid=APPID&secret=APPSECRET";
        String requestUrl = tokenUrl.replace("APPID", WxConfig.APPID).replace("APPSECRET", WxConfig.APPSECRECT);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                token = new AccessToken();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
                        jsonObject.getString("errmsg"));
            }
        }
        return token;
    }

}
