package com.bupt.weibo.dto;

import lombok.Data;

@Data
public class MessageLikesDTO {
    private int messageId;
    private int srcId;
    private String content;
    private String nickName;
}
