package com.bupt.weibo.exception;

import com.bupt.weibo.entity.enums.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */


//todo:使用抛出异常
public class ResultException extends RuntimeException{
    private ErrorCode errorCode;

    public ResultException() {
    }

    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ResultException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ResultException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @JsonIgnore
    public ResultException getResultExceptionWithoutCause(){
        return new ResultException(this.getMessage(),this.getErrorCode());
    }
}