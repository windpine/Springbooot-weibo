package com.bupt.weibo.controller;

import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public User getUser(){

        User user=userService.getUser();

        log.info("username: "+user.getNickname());

        return user;
    }
}
