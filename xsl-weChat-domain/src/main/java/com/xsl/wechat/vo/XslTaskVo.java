package com.xsl.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/19 13:03
 */
public class XslTaskVo {

    /**任务id*/
    private String taskId;

    @JsonProperty("title")
    private String taskTitle;

    /**任务类型，任务类别名称*/
    private String type;

    /**悬赏金额*/
    private BigDecimal money;

    /**发布任务时间*/
    @JsonProperty("issueTime")
    private Date createDate;

    /**悬赏地址*/
    private String address;

    /**悬赏人姓名*/
    @JsonProperty("nickName")
    private String name;

    private String avatarUrl;

    /**悬赏区域*/
    private String addressNoun;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddressNoun() {
        return addressNoun;
    }

    public void setAddressNoun(String addressNoun) {
        this.addressNoun = addressNoun;
    }
}
