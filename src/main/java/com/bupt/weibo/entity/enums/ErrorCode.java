package com.bupt.weibo.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  ErrorCode {
    ERROR(40000,"error"),
    NOTAUTHC(40100,"not authc"),
    NOTAUTHZ(40101,"not authz"),
    USERNAMEORPASSWORD(40401,"用户名或密码不正确"),
    USEREXIST(40402,"用户名已存在");
    NOFOLLOWORFOLLOWER(40403,"no follows or followers");

    private final int code;
    private final String desc;

    ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
