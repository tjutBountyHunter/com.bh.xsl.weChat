package com.xsl.wechat.vo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 11:33
 */
public class AuthenticationReqVo {

    private String userId;

    private String name;

    private String phoneNumber;

    private String address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
