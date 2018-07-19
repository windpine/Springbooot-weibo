package com.bupt.weibo.controller;


import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Topic;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.TopicService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value=TopicController.PATH)
public class TopicController {
    public static final String PATH="/topics";

    //创建日志记录
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TopicService topicService;

    @GetMapping
    //获取全部话题
    public ResponseEntity<ResultDTO> getAllTopics(UriComponentsBuilder uriComponentsBuilder){
        //包装header
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        List<Topic> topics=topicService.getAllTopics();
        Map<String,List<Topic>> result = new HashMap<String,List<Topic>>();
        if(topics.size()!=0){
            result.put("topicList",topics);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no topic or error");
        }
    }
}
