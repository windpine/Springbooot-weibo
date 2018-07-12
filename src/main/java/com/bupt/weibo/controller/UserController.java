package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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


    @PostMapping("/login")
    public ResultDTO login(@RequestParam("username") String username,
                             @RequestParam("password")String password){

        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            currentUser.login(token);
            // 判断当前用户是否登录
            if (currentUser.isAuthenticated() == true) {
                return ResultUtils.onSuccess();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return ResultUtils.onError(e.getMessage());
        }finally {
            return ResultUtils.onError();
        }

    }

    //根据用户ID获得一个用户的信息
    @GetMapping("/{uid}")
    public ResultDTO getUser(@PathVariable("uid") Integer uid){

        User user=userService.getUser(uid);


        //log.info("username: "+user.getNickname());

        return resultUtils.onSuccess(JSONObject.toJSONString(user));
    }

    //获得所有用户
    @GetMapping("/users")
    public ResultDTO getUsers(){
        List<User> users=userService.getUsers();
        return resultUtils.onSuccess(JSONObject.toJSONString(users));
    }


    //新用户注册
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
