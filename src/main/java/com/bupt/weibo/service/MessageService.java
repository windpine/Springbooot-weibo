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
    //获得某一用户某种消息
    List<MessageDTO> getPersonalTypeOfMessage(String UID,Integer type);
    //发布一条消息
    Boolean publishMessage(Message message);
    //删除一条消息
    Boolean deleteMessage(String UID,Integer messageID);
    //检查关注的人是否发布新消息
    void checkFollowedTweet(String followUID);
}
