package com.bupt.weibo.dto;

import com.bupt.weibo.entity.enums.MessageType;
import lombok.Data;

@Data
public class MessageDTO {
    public Integer messageID;
    public String nickName;
    public String content;
    public Integer messageType;
    public String srcUID;
}
