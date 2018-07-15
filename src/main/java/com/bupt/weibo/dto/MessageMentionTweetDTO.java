package com.bupt.weibo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageMentionTweetDTO {
    private int messageId;
    private int srcId;
    private String content;
    private String nickName;
    private Timestamp createTime;
}
