package com.example.demo.common;

/**
 * @author limeng
 * @date 2019/8/8
 */
public class ResponseMessage {
    private int code;
    private String msg;
    private Object data;
    public ResponseMessage(){

    }

    /**
     * @description：成功返回
     * @author: limeng
     * @date: 2019/8/27
     * @param: data
     * @return: com.example.demo.common.ResponseMessage
     */
    public static ResponseMessage Success(Object data){
        return new ResponseMessage(Status.SUCCESS.getCode(),Status.SUCCESS.getMessage(),data);
    }

    /**
     * @description：失败返回
     * @author: limeng
     * @date: 2019/8/27
     * @param: status
     * @return: com.example.demo.common.ResponseMessage
     */
    public static ResponseMessage failed(Status status){
        return new ResponseMessage(status.getCode(),status.getMessage(),null);
    }

    public ResponseMessage(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
