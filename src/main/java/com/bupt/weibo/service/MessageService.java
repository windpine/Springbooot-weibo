package com.bupt.weibo.service;

import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.entity.Message;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/14
 * @description
 */
public interface MessageService {
    //获得某一用户所有消息
    List<MessageDTO> getPersonalAllMessage(Integer UID);
    //发布一条消息
    Boolean publishMessage(Message message);
}
