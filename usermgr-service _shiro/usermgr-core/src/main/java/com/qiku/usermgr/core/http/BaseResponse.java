package com.qiku.usermgr.core.http;



/**
 * Created by houxianyong 2020-2-25.
 */
public class BaseResponse {

    private int rcode;
    private String rmsg;
    private Object data;
    private long total;

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public String getRmsg() {
        return rmsg;
    }

    public void setRmsg(String rmsg) {
        this.rmsg = rmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public static BaseResponse Ok() {
        BaseResponse response = new BaseResponse();
        response.setRcode(RtnEnum.SUCCESS.getCode());
        response.setRmsg("OK");
        return response;
    }

    public static BaseResponse Fail(String msg) {
        BaseResponse response = new BaseResponse();
        response.setRcode(RtnEnum.CODE_FAIL.getCode());
        response.setRmsg(msg);
        return response;
    }

    public static BaseResponse Exp(String msg) {
        BaseResponse response = new BaseResponse();
        response.setRcode(RtnEnum.CODE_EXCEPTION.getCode());
        response.setRmsg(msg);
        return response;
    }

    public static BaseResponse Ret(int code, String msg) {
        BaseResponse response = new BaseResponse();
        response.setRcode(code);
        response.setRmsg(msg);
        return response;
    }

    public static BaseResponse Ret(RtnEnum rtnEnum) {
        BaseResponse response = new BaseResponse();
        response.setRcode(rtnEnum.getCode());
        response.setRmsg(rtnEnum.getMsg());
        return response;
    }

    public BaseResponse putData(Object data) {
        this.setData(data);
        return this;
    }

    public BaseResponse putRmsg(String msg) {
        this.setRmsg(msg);
        return this;
    }

    public BaseResponse putRcode(int code) {
        this.setRcode(code);
        return this;
    }

    public BaseResponse putTotal(long total) {
        this.setTotal(total);
        return this;
    }

    public String toString(){
        return "{\"rcode\":"+ this.getRcode() +",\"rmsg\":"+ this.getRmsg() +",\"total\":"+ this.getTotal() +",\"data\":"+ this.getData() +"}";
    }
}