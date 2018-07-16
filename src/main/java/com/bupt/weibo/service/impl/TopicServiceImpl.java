package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Topic;
import com.bupt.weibo.repository.TopicRepository;
import com.bupt.weibo.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }


}
