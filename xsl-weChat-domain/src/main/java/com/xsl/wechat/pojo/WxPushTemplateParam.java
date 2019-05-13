package com.xsl.wechat.pojo;

/**
 * 推送模板参数
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/12 0:08
 */
public class WxPushTemplateParam {

    /**参数名*/
//    private String name;

    /**参数值*/
    private String value;

    public WxPushTemplateParam(String value) {
//        this.name = name;
        this.value = value;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
