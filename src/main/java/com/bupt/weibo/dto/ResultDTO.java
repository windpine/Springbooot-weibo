package com.bupt.weibo.dto;

import com.bupt.weibo.entity.enums.ErrorCode;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Data
public class ResultDTO {

    //仅用于一定情况
    private ErrorCode errorCode;
    private String status;
    private Object data;
    private String msg;
    private String url;
}
