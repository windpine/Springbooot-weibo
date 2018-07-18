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
    /*//获得某一用户所有@消息
    List<MessageMentionTweetDTO> getPersonalAllTweetMention(String UID);
    //获得某一用户所有评论消息
    List<MessageCommentDTO> getPersonalAllComment(String UID);*/
    //获得某一用户某种消息
    List<MessageDTO> getPersonalTypeOfMessage(String UID,Integer type);
    //发布一条消息
    Boolean publishMessage(Message message);
    //获得某一用户所有被点赞消息
    //List<MessageTweetDTO> getPersonalAllLikes(String UID);
    //删除一条消息
    Boolean deleteMessage(String UID,Integer messageID);
    //检查关注的人是否发布新消息
    void checkFollowedTweet(String followUID);
    /*//获得用户所有关注的人的消息
    List<MessageTweetDTO> getPersonalAllFollowedTweet(String UID);
    //获得用户被转发的人的消息
    List<MessageTweetDTO> getPersonalForwardTweet(String UID);*/
}
