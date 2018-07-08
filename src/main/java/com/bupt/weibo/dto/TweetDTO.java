package com.bupt.weibo.dto;

import lombok.Data;

/**
 * 获取tweet
 */
@Data
public class TweetDTO {
    private Integer uid;
    private String content;
}
