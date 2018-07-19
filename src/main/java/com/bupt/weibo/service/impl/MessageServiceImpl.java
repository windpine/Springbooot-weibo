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

    @Override
    public List<MessageDTO> getPersonalTypeOfMessage(String UID, Integer type) {
        if(type.intValue() != MessageType.FOLLOWEDTWEET.getType()) {
            List<Object[]> objs = messageRepository.findMessageUserByUidAndType(UID, type);
            List<MessageDTO> messageDTOS = new LinkedList<>();
            for (Object[] objects : objs) {
                //将获取到的MessageUser转换为对应类型
                MessageDTO messageDTO = messageMapper.convertToDto((Message) objects[0], (User) objects[1]);
                messageDTOS.add(messageDTO);
            }
            return messageDTOS;
        }else{
            List<Object[]> objs= relationRepository.findRelationTweet(UID);
            List<MessageDTO> messageDTOS = new  LinkedList<>();
            for(Object[] objects:objs){
                MessageDTO messageDTO = messageMapper.convertToDto((Relation)objects[0],(Tweet)objects[1],(User)objects[2]);
                messageDTO.setMessageID(-1);
                messageDTO.setMessageType(MessageType.FOLLOWEDTWEET.getType());
                messageDTOS.add(messageDTO);
            }
            return messageDTOS;
        }
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
