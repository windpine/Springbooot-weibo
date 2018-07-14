package com.bupt.weibo.controller;

import com.bupt.weibo.dto.MessageCommentDTO;
import com.bupt.weibo.dto.MessageMentionTweetDTO;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Message;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.MessageService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @anthor tanshangou
 * @time 2018/7/14
 * @description
 */
@RestController
@RequestMapping(MessageController.PATH)
@Slf4j
public class MessageController {
    public static final String PATH = "/message";
    public static final String UIDPATH = "/{UID}";
    public static final String MENTIONPATH ="/mention";
    public static final String COMMENTPATH= "/comment";
    @Autowired
    MessageService messageService;

    /**
     * 获取当前用户下的所有@消息
     */
    @GetMapping(value = PATH+MENTIONPATH+UIDPATH)
    public ResponseEntity<ResultDTO> getAllMessageMention(UriComponentsBuilder uriComponentsBuilder, @PathVariable(name="UID")String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+MENTIONPATH+"/"+UID);
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
        //map message
        List<MessageMentionTweetDTO> messageDTOS = messageService.getPersonalAllTweetMention(UID);
        Map result = new HashMap<String,List<MessageMentionTweetDTO>>();
        //返回
        if(messageDTOS.size() != 0){
            result.put("messageList",messageDTOS);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no message or error");
        }
    }
    /**
     * 获取当前用户下的所有评论消息
     */
    @GetMapping(value = PATH+COMMENTPATH+UIDPATH)
    public ResponseEntity<ResultDTO> getAllMessageComment(UriComponentsBuilder uriComponentsBuilder, @PathVariable(name="UID")String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+COMMENTPATH+"/"+UID);
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
        //map message
        List<MessageCommentDTO> messageDTOS = messageService.getPersonalAllComment(UID);
        Map result = new HashMap<String,List<MessageCommentDTO>>();
        //返回
        if(messageDTOS.size() != 0){
            result.put("messageList",messageDTOS);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no message or error");
        }
    }

    /**
     * 为当前用户推送一条消息
     *
     */
    @PostMapping
    public ResponseEntity<ResultDTO> publishMessage(UriComponentsBuilder uriComponentsBuilder,@RequestBody Message message){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
        //
        boolean ispublish = messageService.publishMessage(message);
        if(ispublish){
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("publish error");
        }
    }
}
