package com.xsl.weChat.common.enums;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/29 0:48
 */
public enum UserStateEnum {

    /**
     * 用户状态
     */
    CHECK_PENDING((byte)0, "用户还未审核"),
    NORMAL((byte)1, "正常"),
    FREEZE((byte)-1, "用户冻结"),
    CHECK_PENDING_EXCEPTION((byte)-2, "用户审核未通过"),
    DELETE((byte)-3, "用户已被删除");
    private Byte code;
    private String info;
    ;

    private Byte state;

    private String msg;

    UserStateEnum(Byte state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Byte getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
