package com.xsl.wechat.dto;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/30 14:37
 */
public class XslTaskDTO {

    private String id;

    private String title;

    private String type;

    private String money;

    private String issueImg;

    private String issueTime;

    private String address;

    private String addressNoun;

    private Byte missionState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIssueImg() {
        return issueImg;
    }

    public void setIssueImg(String issueImg) {
        this.issueImg = issueImg;
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

    public String getAddressNoun() {
        return addressNoun;
    }

    public void setAddressNoun(String addressNoun) {
        this.addressNoun = addressNoun;
    }

    public Byte getMissionState() {
        return missionState;
    }

    public void setMissionState(Byte missionState) {
        this.missionState = missionState;
    }
}
