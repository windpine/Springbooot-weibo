package com.bupt.weibo.controller;

import com.bupt.weibo.dto.MessageDTO;
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

    @Autowired
    MessageService messageService;

    /**
     * 获取当前用户下的所有消息
     */
    @GetMapping(value = UIDPATH)
    public ResponseEntity<ResultDTO> getAllMessage(UriComponentsBuilder uriComponentsBuilder, @PathVariable(name="UID")Integer UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+UID);
        List<MessageDTO> messageDTOS = messageService.getPersonalAllMessage(UID);

        Map result = new HashMap<String,List<MessageDTO>>();
        result.put("MessageList",messageDTOS);
        if(messageDTOS.size() != 0){
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
        boolean ispublish = messageService.publishMessage(message);
        if(ispublish){
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("publish error");
        }
    }
}
