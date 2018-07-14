package com.bupt.weibo.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private int messageId;
    private int type;
    private int srcId;
    private String content;
}
