package com.bupt.weibo.controller;

import com.bupt.weibo.dto.MessageCommentDTO;
import com.bupt.weibo.dto.MessageLikesDTO;
import com.bupt.weibo.dto.MessageMentionTweetDTO;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Message;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.MessageService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.rmi.server.UID;
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
    public static final String LIKESPATH="/likes";

    @Autowired
    MessageService messageService;
    //日志记录
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    /**
     * 获取当前用户下的所有消息
     */
    @GetMapping(value = MENTIONPATH)
    public ResponseEntity<ResultDTO> getAllMessageMention(UriComponentsBuilder uriComponentsBuilder, @RequestParam(name="UID")String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+MENTIONPATH);
        //map message
        List<MessageMentionTweetDTO> messageDTOS = messageService.getPersonalAllTweetMention(UID);
        Map result = new HashMap<String,List<MessageMentionTweetDTO>>();
        //返回
        result.put("messageList",messageDTOS);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);

    }
    /**
     * 获取当前用户下的所有评论消息
     */
    @GetMapping(value = COMMENTPATH)
    public ResponseEntity<ResultDTO> getAllMessageComment(UriComponentsBuilder uriComponentsBuilder, @RequestParam(name="UID")String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+COMMENTPATH);
        //map message
        List<MessageCommentDTO> messageDTOS = messageService.getPersonalAllComment(UID);
        Map result = new HashMap<String,List<MessageCommentDTO>>();
        //返回
        result.put("messageList",messageDTOS);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);

    }
    /**
     * 提示用户有人点赞
     */
    @GetMapping(value = LIKESPATH)
    public ResponseEntity<ResultDTO> getAllMessageLike(UriComponentsBuilder uriComponentsBuilder,@RequestParam(name="UID")String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+LIKESPATH+"/"+UID);
        //map message
        List<MessageLikesDTO> likesDTOList = messageService.getPersonalAllLikes(UID);
        Map result = new HashMap<String,List<MessageLikesDTO>>();
        //返回
        result.put("messageList",likesDTOList);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);

    }
    /**
     * 为当前用户推送一条消息
     *
     */
    @PostMapping
    public ResponseEntity<ResultDTO> publishMessage(UriComponentsBuilder uriComponentsBuilder,@RequestBody Message message){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        boolean ispublish = messageService.publishMessage(message);
        if(ispublish){
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("publish error");
        }
    }
    /**
     * 为当前用户删除指定消息
     */
    @DeleteMapping
    public ResponseEntity<ResultDTO> deleteMessage(UriComponentsBuilder uriComponentsBuilder, @RequestParam(name = "UID")String UID, @RequestParam(name ="messageID")Integer messageID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        //删除
        if(messageService.deleteMessage(UID,messageID)) {
            ResponseEntity<ResultDTO> response = new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(), headers, HttpStatus.OK);
            logger.info("删除成功");
            return response;
        }else {
            logger.info("删除失败");
            throw new ResultException("删除失败");
        }
    }
}