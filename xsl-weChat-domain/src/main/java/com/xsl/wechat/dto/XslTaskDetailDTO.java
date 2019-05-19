package com.xsl.wechat.dto;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/2 13:46
 */
public class XslTaskDetailDTO {

    private String title;

    private String type;

    private String money;

    private List<String> issueImg;

    private String issueTime;

    private String address;

    private String addressNoun;

    private Byte missionState;

    private String missionDetail;

    private String nickName;

    private String avatarUrl;

    private String acceptUserName;

    private String acceptUserAvatarUrl;

    private String phoneNum;

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

    public List<String> getIssueImg() {
        return issueImg;
    }

    public void setIssueImg(List<String> issueImg) {
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

    public String getMissionDetail() {
        return missionDetail;
    }

    public void setMissionDetail(String missionDetail) {
        this.missionDetail = missionDetail;
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

    public String getAcceptUserName() {
        return acceptUserName;
    }

    public void setAcceptUserName(String acceptUserName) {
        this.acceptUserName = acceptUserName;
    }

    public String getAcceptUserAvatarUrl() {
        return acceptUserAvatarUrl;
    }

    public void setAcceptUserAvatarUrl(String acceptUserAvatarUrl) {
        this.acceptUserAvatarUrl = acceptUserAvatarUrl;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
