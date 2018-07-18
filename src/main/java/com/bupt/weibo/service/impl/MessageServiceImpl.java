package com.bupt.weibo.service.impl;


import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.dto.mapper.MessageMapper;
import com.bupt.weibo.entity.*;
import com.bupt.weibo.entity.enums.MessageType;
import com.bupt.weibo.repository.MessageRepository;
import com.bupt.weibo.repository.RelationRepository;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/14
 * @description
 */

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    TweetRepository tweetRespository;

    /*@Override
    public List<MessageMentionTweetDTO> getPersonalAllTweetMention(String UID) {
        List<Object[]> resultArray=messageRepository.findAllMessageAndTweetJoin(UID);
        List<MessageMentionTweetDTO> messageMentionTweetDTOList = new LinkedList<>();
        for(Object[] objArray:resultArray){
            messageMentionTweetDTOList.add(messageMapper.convertToMentionDto((Message)objArray[0],(Mention)objArray[1],(Tweet)objArray[2],(User)objArray[3]));
        }
        return messageMentionTweetDTOList;
    }

    @Override
    public List<MessageCommentDTO> getPersonalAllComment(String UID) {
        List<Object[]> resultArray=messageRepository.findAllMessageAndCommentJoin(UID);
        List<MessageCommentDTO> messageCommentDTOList = new LinkedList<>();
        for(Object[] objArray:resultArray){
            messageCommentDTOList.add(messageMapper.convertToCommentDto((Message)objArray[0],(Comment)objArray[1],(User)objArray[2]));
        }
        return messageCommentDTOList;
    }
    @Override
    public List<MessageTweetDTO> getPersonalAllLikes(String UID){
        List<Object[]> resultArray = messageRepository.findAllTweetMessage(UID,MessageType.LIKES.getType());
        List<MessageTweetDTO> messageTweetDTOList = new LinkedList<>();
        for(Object[] objArray:resultArray){
            messageTweetDTOList.add(messageMapper.convertToDto((Message)objArray[0],(Tweet)objArray[1],(User)objArray[2]));
        }
        return messageTweetDTOList;
    }*/

    @Override
    @Transactional
    public Boolean deleteMessage(String UID, Integer messageID) {
        if(messageRepository.existsById(messageID)){
            Message message = messageRepository.getOne(messageID);
            if(message.getUid().equals(UID)) {
                messageRepository.delete(message);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void checkFollowedTweet(String UID) {
        //查找关注人
        List<User> followeds = relationRepository.findFollowersByFollowId(UID);
        List<Message> messages = messageRepository.findMessagesByUidAndType(UID, MessageType.FOLLOWEDTWEET.getType());
        for(User user: followeds){
            List<Tweet> tweets = tweetRespository.findTweetsByUid(user.getUid(),new Sort(Sort.Direction.DESC,"createTime"));
            for(Tweet tweet:tweets){
                //检查是否已经发表
                boolean isPublish = false;
                for(Message message:messages){
                    if(message.getSrcId() == tweet.getTid()){
                        isPublish = true;
                    }
                }
                if(!isPublish){
                    Message message = new Message();
                    message.setUid(UID);
                    message.setType(MessageType.FOLLOWEDTWEET.getType());
                    message.setSrcId(tweet.getTid());
                    message.setSrcUid(tweet.getUid());
                    message.setContent(tweet.getContent());
                    messageRepository.save(message);
                }
            }
        }
    }

   /* @Override
    public List<MessageTweetDTO> getPersonalAllFollowedTweet(String UID) {
        List<Object[]> resultArray = messageRepository.findAllTweetMessage(UID,MessageType.FOLLOWEDTWEET.getType());
        List<MessageTweetDTO> messageTweetDTOList = new LinkedList<>();
        for(Object[] objArray:resultArray){
            messageTweetDTOList.add(messageMapper.convertToDto((Message)objArray[0],(Tweet)objArray[1],(User)objArray[2]));
        }
        return messageTweetDTOList;
    }

    @Override
    public List<MessageTweetDTO> getPersonalForwardTweet(String UID) {
        return null;
    }*/

    @Override
    public List<MessageDTO> getPersonalTypeOfMessage(String UID, Integer type) {
        List<Object[]> objs = messageRepository.findMessageUserByUidAndType(UID,type);
        List<MessageDTO> messageDTOS = new LinkedList<>();
        for(Object[] objects:objs){
            //将获取到的MessageUser转换为对应类型
            MessageDTO messageDTO = messageMapper.convertToDto((Message)objects[0],(User)objects[1]);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

    @Override
    public Boolean publishMessage(Message message) {
        Message savedMessage = messageRepository.saveAndFlush(message);
        if(savedMessage.equals(message)){
            return true;
        }else{
            return false;
        }
    }

}
