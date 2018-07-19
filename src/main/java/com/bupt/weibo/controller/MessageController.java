package com.bupt.weibo.controller;

import com.bupt.weibo.dto.*;
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

    @Autowired
    MessageService messageService;
    //日志记录
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    /**
     * 获取某一用户消息
     * @param uriComponentsBuilder
     * @param UID 当前用户UID
     * @param type 消息类型
     * @return
     */
    @GetMapping
    public ResponseEntity<ResultDTO> getUserMessage(UriComponentsBuilder uriComponentsBuilder,@RequestParam(name="UID")String UID,@RequestParam(name="type")Integer type){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder, PATH);
        //map message
        List<MessageDTO> tweetDTOList = messageService.getPersonalTypeOfMessage(UID,type);
        logger.info("获得所有转发微博");
        Map result = new HashMap<String, List<MessageDTO>>();
        //返回
        if(tweetDTOList != null) {
            result.put("messageList", tweetDTOList);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result), headers, HttpStatus.OK);
        }else{
            throw new ResultException("获取失败");
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