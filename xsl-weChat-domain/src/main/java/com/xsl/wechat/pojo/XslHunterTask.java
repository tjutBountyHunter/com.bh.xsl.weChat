package com.xsl.wechat.pojo;

import java.util.Date;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/1 21:24
 */
public class XslHunterTask {

    private Integer id;

    private String hunterId;

    private String taskId;

    private Date createDate;

    private Date updateDate;

    private Byte taskState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHunterId() {
        return hunterId;
    }

    public void setHunterId(String hunterId) {
        this.hunterId = hunterId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Byte getTaskState() {
        return taskState;
    }

    public void setTaskState(Byte taskState) {
        this.taskState = taskState;
    }
}
