package com.bupt.weibo.dto;

/**
 * @anthor tanshangou
 * @time 2018/7/16
 * @description
 */
public class Response {
    private String responseMessage;

    public Response(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
