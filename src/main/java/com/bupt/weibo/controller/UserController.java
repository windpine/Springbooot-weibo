package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.enums.ErrorCode;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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


    //根据用户ID获得一个用户的信息
    @GetMapping("/{uid}")
    public ResultDTO getUser(@PathVariable("uid") String uid){

        User user=userService.getUserById(uid);


        //log.info("username: "+user.getNickname());

        return resultUtils.onSuccess(JSONObject.toJSONString(user));
    }

    //获得所有用户
    @GetMapping("/users")
    public ResultDTO getUsers(){
        List<User> users=userService.listUsers();
        return resultUtils.onSuccess(JSONObject.toJSONString(users));
    }


    //新用户注册
    @PostMapping("/")
    public ResponseEntity<User> Register(@RequestBody UserDTO userDTO,
                              UriComponentsBuilder uriComponentsBuilder) throws Exception{
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"users");
        if(userDTO != null){
            log.info("=======开始注册用户=========");
            log.info("username:"+ userDTO.getUsername()
                    +" password: "+userDTO.getPassword());
            User user=userService.registerUser(userDTO);
            return new ResponseEntity<User>(user,headers,HttpStatus.OK);
        }else{
            throw new ResultException("Username:"+userDTO.getUsername(),ErrorCode.USEREXIST);
        }


    }




}

//URL点击 ：<a href="details.html" th:href="@{myThymeleaf(orderId=${id})}">view</a></br>
//        相对路径：<img th:width="100px" th:src="@{../images/{imageUrl}(imageUrl=${image})}"></br>
//        绝对路径：<img th:width="100px" th:src="@{/images/{imageUrl}(imageUrl=${image})}"></br>
//<!-- 无效链接 -->
//        其他路径：<img th:width="100px" th:src="@{images/{imageUrl}(imageUrl=${image})}">
