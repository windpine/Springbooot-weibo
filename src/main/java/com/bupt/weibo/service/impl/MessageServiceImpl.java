package com.bupt.weibo.service.impl;


import com.bupt.weibo.dto.MessageCommentDTO;
import com.bupt.weibo.dto.MessageMentionTweetDTO;
import com.bupt.weibo.dto.mapper.MessageMapper;
import com.bupt.weibo.entity.*;
import com.bupt.weibo.repository.MessageRepository;
import com.bupt.weibo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    MessageRepository messageRepository;
    @Override
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
    public Boolean publishMessage(Message message) {
        Message savedMessage = messageRepository.saveAndFlush(message);
        if(savedMessage.equals(message)){
            return true;
        }else{
            return false;
        }
    }
}
