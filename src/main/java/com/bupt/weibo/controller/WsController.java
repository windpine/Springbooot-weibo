package com.bupt.weibo.controller;

import com.bupt.weibo.config.Constant;
import com.bupt.weibo.dto.Message;
import com.bupt.weibo.dto.Response;
import com.bupt.weibo.service.impl.WebSocketService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/16
 * @description
 */
@Controller
public class WsController {

    @Autowired
    WebSocketService webSocketService;

    // 测试地址 http://127.0.0.1:8080/index.html?userId=admin
    @MessageMapping(Constant.FORETOSERVERPATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo(Constant.PRODUCERPATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public Response say(Message message) throws Exception {
        List<String> users = Lists.newArrayList();
        users.add(message.getName());//此处只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.send2Users(users, new Response("hello " + message.getName()));

        return new Response("Welcome, " + message.getName() + "!");
    }

    @RequestMapping("/templates")
    String test() {
        //逻辑处理
        return "/index";
    }
}