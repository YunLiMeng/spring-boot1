package com.example.demo.common;

/**
 * @author limeng
 * @date 2019/8/8
 */
public enum Status {
    SUCCESS(200,"ok"),INVALID_PARAM(400,"invalid parameter"),
    ACCESS_DENY(401,"access deny"),USERNAME_NOT_EXIST(600,"user not exist"),OPERATION_FAIL(601,"操作失败"),
    WX_SERVER_EXCEPT(602,"微信服务异常"),REPEAT_VOTE(603,"重复投票"),VOTE_NOT_START(606,"投票还没开始"),VOTE_STOPPED(604,"投票已经结束"),NOT_GOING_VOTE(605,"没有进行中的投票"),VOTE_NOT_END(607,"投票尚未结束"),
    USER_EXISTS(608,"当前用户名已被使用"),ID_CARD_EXISTS(609,"当前身份证号已被使用"),ID_CARD_NOT_EXISTS_FOR_ORG(610,"该身份证没有对应的机构信息"),MEETING_NOT_PERFECT(611,"当前会议没有完善信息");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
