package com.xsl.weChat.common.enums;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/3 20:50
 */
public enum TaskStateEnum {
    /**
     * 任务状态及信息
     */
    TASK_WAIT((byte) 0,"任务代接收"),

    HUNTER_IN_DIRECTING((byte)1,"启动推荐算法待接收"),

    TASK_ONGOING((byte)2,"任务进行中"),

    TASK_FINISH((byte)3,"任务完成"),

    TASK_FREEZE((byte)-1,"任务冻结"),

    TASK_TO_WITHDRAW((byte)-2,"任务撤回"),
    ;

    private Byte state;

    private String msg;

    TaskStateEnum(Byte state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Byte getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
