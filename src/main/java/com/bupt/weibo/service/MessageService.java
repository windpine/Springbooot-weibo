package com.bupt.weibo.service;

import com.bupt.weibo.dto.MessageCommentDTO;
import com.bupt.weibo.dto.MessageMentionTweetDTO;
import com.bupt.weibo.entity.Message;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/14
 * @description
 */
public interface MessageService {
    //获得某一用户所有@消息
    List<MessageMentionTweetDTO> getPersonalAllTweetMention(String UID);
    //获得某一用户所有评论消息
    List<MessageCommentDTO> getPersonalAllComment(String UID);
    //发布一条消息
    Boolean publishMessage(Message message);
}
