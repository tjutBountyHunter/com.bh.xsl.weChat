package com.xsl.wechat.vo;

import com.xsl.wechat.pojo.WxPushTemplateParam;

import java.util.HashMap;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/11 23:52
 */
public class WxPushTemplate {

    /** 消息接收方*/
    private String touser;

    /**推送模板id*/
    private String template_id;

    /** 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转*/
    private String page;

    /**表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id*/
    private String form_id;

    /**推送模板*/
    HashMap<String,WxPushTemplateParam> data;

    /**模板需要放大的关键词，不填则默认无放大*/
    private String emphasis_keyword;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }


    public HashMap<String, WxPushTemplateParam> getData() {
        return data;
    }

    public void setData(HashMap<String, WxPushTemplateParam> data) {
        this.data = data;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

}
