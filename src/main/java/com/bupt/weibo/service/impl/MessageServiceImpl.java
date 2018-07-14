package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.dto.mapper.MessageMapper;
import com.bupt.weibo.entity.Message;
import com.bupt.weibo.repository.MessageRepository;
import com.bupt.weibo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<MessageDTO> getPersonalAllMessage(Integer UID) {
        List<Message> messages=messageRepository.findAllByUid(UID);
        List<MessageDTO> messageDTOList = messageMapper.convertToListDto(messages);
        return messageDTOList;
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
