package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.enums.UserStateEnum;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.CommonUtil;
import com.xsl.weChat.service.XslGetWeChatService;
import com.xsl.wechat.mapper.XslFileMapper;
import com.xsl.wechat.mapper.XslHunterMapper;
import com.xsl.wechat.mapper.XslMasterMapper;
import com.xsl.wechat.mapper.XslUserMapper;
import com.xsl.wechat.pojo.*;
import com.xsl.wechat.vo.WeChatAccountConfig;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 获取微信openid
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 17:10
 */
@Service
public class XslGetWeChatServiceImpl implements XslGetWeChatService {

    @Autowired
    private XslMasterMapper xslMasterMapper;

    @Autowired
    private XslHunterMapper xslHunterMapper;

    @Autowired
    private XslUserMapper xslUserMapper;

    @Autowired
    private XslFileMapper xslFileMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(XslGetWeChatServiceImpl.class);

    @Transactional(rollbackFor = RuntimeException.class)
    public XslResult getOpenIdAndSessionKey(String code, String nickName, String avatarUrl) {
        if(StringUtils.isEmpty(code)){
            return XslResult.build(403,"code不能为空");
        }
        try {

            String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=wxb7715d658f00b0e1&secret=d95be64495bf3a94114bae2841a54961&js_code=" + code +"&"+
                    "grant_type=authorization_code";
            JSONObject jsonObject = CommonUtil.httpsRequest(WX_URL, "GET", null);
            String userId = (String) jsonObject.get("openid");
            if(jsonObject!=null&&!StringUtils.isEmpty(userId)){
                WeChatAccountConfig weChatAccountConfig = new WeChatAccountConfig();
                weChatAccountConfig.setOpenId(userId);
                weChatAccountConfig.setSessionKey(jsonObject.getString("session_key"));
                XslUserExample example = new XslUserExample();
                XslUserExample.Criteria criteria = example.createCriteria();
                criteria.andUseridEqualTo(userId);
                List<XslUser> list = xslUserMapper.selectByExample(example);
                if(list!=null&&list.size()>0){
                    weChatAccountConfig.setState(list.get(0).getState());
                    return XslResult.build(1,"正常",weChatAccountConfig);
                }
                weChatAccountConfig.setState(list.get(0).getState());
                XslUser xslUser = new XslUser();
                xslUser.setUserid(userId);
                xslUser.setName(nickName);
                XslMaster xslMaster = initXslMaster(xslUser);
                XslHunter xslHunter = initXslHunter(xslUser);
                initXslFile(xslUser,avatarUrl);
                initUserInfo(xslUser,xslHunter,xslMaster);
                return XslResult.build(1,"正常",weChatAccountConfig);
            }
        }catch (Exception e){
            LOGGER.error("服务器异常警告："+e.getMessage());
            return XslResult.build(500, "服务器异常");
        }
        return XslResult.build(500,"服务器异常");
    }

    /**
     * 初始化雇主信息
     * @param xslUser
     * @return
     */
    private XslMaster initXslMaster(XslUser xslUser) {
        //初始化雇主信息
        XslMaster xslMaster = new XslMaster();
        xslMaster.setUserid(xslUser.getUserid());
        xslMaster.setMasterid(UUID.randomUUID().toString());
        xslMaster.setLevel((short) 1);
        xslMaster.setDescr("新人雇主");
        xslMaster.setLastaccdate(new Date());
        try {
            int result = xslMasterMapper.insertSelective(xslMaster);

            if (result < 1){
                throw new RuntimeException("雇主信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
        return xslMaster;
    }

    /**
     * 初始化猎人信息
     * @param xslUser
     * @return
     */
    private XslHunter initXslHunter(XslUser xslUser) {
        //初始化猎人信息
        XslHunter xslHunter = new XslHunter();
        xslHunter.setUserid(xslUser.getUserid());
        xslHunter.setHunterid(UUID.randomUUID().toString());
        xslHunter.setLevel((short) 1);
        xslHunter.setDescr("新手猎人");
        xslHunter.setLasttime(new Date());
        try {
            int result = xslHunterMapper.insertSelective(xslHunter);

            if (result < 1){
                throw new RuntimeException("猎人信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }

        return xslHunter;
    }

    private void initXslFile(XslUser xslUser,String avatarUrl){
        //初始化文件信息
        XslFile xslFile = new XslFile();
        xslFile.setFileid(xslUser.getUserid());
        xslFile.setUrl(avatarUrl);
        xslFile.setDescr("微信用户头像");
        xslFile.setCreatedate(new Date());
        try {

            int result = xslFileMapper.insertSelective(xslFile);

            if (result < 1){
                throw new RuntimeException("微信头像图片存储失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    private void initUserInfo(XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
        xslUser.setHunterid(xslHunter.getHunterid());
        xslUser.setMasterid(xslMaster.getMasterid());
        xslUser.setState(UserStateEnum.CHECK_PENDING.getCode());
        xslUser.setSex("男");
        xslUser.setCreatedate(new Date());
        xslUser.setUpdatedate(new Date());
        try {
            int result = xslUserMapper.insertSelective(xslUser);

            if (result < 1){
                throw new RuntimeException("用户信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

}
