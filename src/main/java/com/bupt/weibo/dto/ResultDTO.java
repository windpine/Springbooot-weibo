package com.bupt.weibo.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Data
public class ResultDTO {

    private String status;
    private String data;
    private String msg;
}
