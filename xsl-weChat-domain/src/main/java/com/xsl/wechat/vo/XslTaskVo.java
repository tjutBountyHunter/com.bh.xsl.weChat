package com.xsl.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/19 13:03
 */
public class XslTaskVo {

    /**发送者id*/
    private String userId;

    /**任务id*/
    private String taskId;

    /**任务标题*/
    private String title;

    /**任务类型，任务类别名称*/
    private String type;

    /**悬赏金额*/
    private String money;

    /**发布任务时间*/
    private String issueTime;

    /**悬赏地址*/
    private String address;

    /**悬赏人姓名*/
    private String nickName;

    private String avatarUrl;

    /**悬赏区域*/
    @JsonProperty("address_noun")
    private String addressNoun;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
