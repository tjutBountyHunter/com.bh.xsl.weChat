package com.xsl.wechat.pojo;

import java.util.Date;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/29 11:23
 */
public class XslTaskArea {

    private Integer id;

    private String taskId;

    private String address;

    private String addressNoun;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
