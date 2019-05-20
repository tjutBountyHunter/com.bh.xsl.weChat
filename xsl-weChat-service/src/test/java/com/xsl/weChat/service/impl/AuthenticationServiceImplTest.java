//package com.xsl.weChat.service.impl;
//
//import com.xsl.weChat.service.AuthenticationService;
//import com.xsl.wechat.vo.AuthenticationReqVo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * @author 梁俊伟
// * @version 1.0
// * @date 2019/5/2 12:11
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-mapper.xml"})
//public class AuthenticationServiceImplTest {
//
//    @Autowired
//    private AuthenticationService authenticationServiceImpl;
//
//    @Test
//    public void authenticationUser() {
//        AuthenticationReqVo authenticationReqVo = new AuthenticationReqVo();
//        authenticationReqVo.setUserId("123");
//        authenticationReqVo.setName("宋志勋");
//        authenticationReqVo.setPhoneNumber("18650407112");
//        authenticationReqVo.setAddress("天津理工大学北区");
////        XslResult xslResult = authenticationServiceImpl.AuthenticationUser(authenticationReqVo);
////        Assert.assertEquals(1,new Long(xslResult.getStatus()).longValue());
//    }
//}