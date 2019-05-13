package com.xsl.wechat.vo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 0:16
 */
public class AccessToken {

    /** 获取到的凭证*/
    private String accessToken;

    /**凭证有效时间，单位：秒**/
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
