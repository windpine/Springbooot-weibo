package com.bupt.weibo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageCommentDTO {
    private int messageId;
    private String content;
    private String nickName;
    private Timestamp createTime;
}
