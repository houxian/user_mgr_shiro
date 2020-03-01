package com.qiku.usermgr.core.http;

/**
 * 返回码描述
 */
public enum RtnEnum {
    SUCCESS(0, "成功"),
    CODE_FAIL(1, "操作失败"),
    CODE_EXCEPTION(2, "操作异常"),
    CODE_UNSUPPORT_METHOD(3, "不支持的请求方式"),
    CODE_UNSUPPORT_HTTP(4, "不支持http协议"),
    CODE_ILLEGAL_REQUEST(5, "非法请求"),
    CODE_MISS_PARAM(6, "参数缺失或异常"),
    CODE_PARSE_JSON_FAIL(7, "json解析失败"),
    CODE_CHECK_SIGN_FAIL(8, "签名鉴权失败"),
    CODE_TOKEN_EXPIRE(9, "令牌过期"),
    CODE_OPERATE_TOO_OFTEN(10, "操作太频繁"),
    CODE_FORBIDDEN_INTERFACE(11, "接口禁用"),
    CODE_FORBIDDEN_USER(12, "用户被禁用"),
    CHECK_CODE_ERROR(14, "验证码不正确"),
    CODE_NOT_EXIST(13, "数据不存在"),
    USER_PWD_ERROR(15, "用户不存在或密码错误"),
    CORP_ALREAD_EXIST(16, "该厂商已存在，请直接添加账号"),
    USER_ALREAD_EXIST(17,"该用户名已经存在"),
    USER_PHONEOREMAIL_EXIST(18,"姓名电话或者邮箱已经存在，请重新输入"),
    TOOL_ALREAD_EXIST(19, "该版本工具已存在"),
    USER_ORI_PWD_ERROR(20, "原始密码输入不正确"),
    CODE_CHECK_USER_ERROR(21, "用戶名已经存在"),
    CODE_CHECK_ORDER_ID_ERROR(22, "工单ID无效"),
    CODE_SESSION_OUT(999, "会话失效");


    private Integer code;

    private String msg;

    RtnEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}