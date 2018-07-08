package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    ResultUtils resultUtils;


    @GetMapping("/{uid}")
    public ResultDTO getUser(@PathVariable("uid") Integer uid){

        User user=userService.getUser(uid);


        //log.info("username: "+user.getNickname());

        return resultUtils.onSuccess(JSONObject.toJSONString(user));
    }

    @GetMapping("/users")
    public ResultDTO getUsers(){
        List<User> users=userService.getUsers();
        return resultUtils.onSuccess(JSONObject.toJSONString(users));
    }


    @PostMapping("/")
    public ResultDTO Register(@RequestBody UserDTO userDTO) throws Exception{
        if(userDTO != null){
            User user=userService.registerUser(userDTO);
            return resultUtils.onSuccess(JSONObject.toJSONString(user));
        }else{
            return resultUtils.onError("userDTO = null");
        }


    }








}
