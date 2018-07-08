package com.bupt.weibo.controller;

import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{uid}")
    public User getUser(@PathVariable("uid") Integer uid){

        User user=userService.getUser(uid);


        log.info("username: "+user.getNickname());

        return user;
    }





}
