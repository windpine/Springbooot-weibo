package com.bupt.weibo.service.impl;

import com.bupt.weibo.config.Constant;
import com.bupt.weibo.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/16
 * @description
 */
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(Response msg) {

        template.convertAndSend(Constant.PRODUCERPATH, msg);
    }

    /**
     * 发送给指定用户
     *
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, Response msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, Constant.P2PPUSHPATH, msg);
        });
    }
}
