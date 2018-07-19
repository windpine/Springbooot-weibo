package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

//    @Test
//    public void getUser() {
//
//        userInfoDTO userInfoDTO = new userInfoDTO();
//        userInfoDTO.setEmail("tsseagull_gd@163.com");
//        userInfoDTO.setUsername("tanshangou");
//        userInfoDTO.setNickname("doggy");
//        userInfoDTO.setPassword("kk6665596");
//        userService.registerUser(userInfoDTO);
//    }
//







    @Test
    public void updateUser(){
        UserInfoDTO userInfoDTO=new UserInfoDTO();
        //userInfoDTO userInfoDTO=new userInfoDTO();
        userInfoDTO.setUid("cb6188d57ed84deea1c32e80f8f4fe41");
        //userInfoDTO.setUid("cb6188d57ed84deea1c32e80f8f4fe41");
        userInfoDTO.setPassword("123");
        userInfoDTO.setAvatarUrl("");
        userInfoDTO.setEmail("nicoleynh@163.com");
        userInfoDTO.setFollowers(0);
        userInfoDTO.setFollows(0);
        userInfoDTO.setTweets(0);
        userInfoDTO.setNickname("nicole");
        userInfoDTO.setUsername("tanshangou");
        userInfoDTO.setSex("女");

        userService.updateUser(userInfoDTO);

    }

    @Test
    public void getUserInfoDTO(){
        UserInfoDTO userInfoDTO=userService.getUserInfoByUid("4881720c5e8a4c7ba781cc2566f35f86");
        log.info("userInfo:",userInfoDTO.getEmail());
    }

    @Test
    public void getAllUsers(){
       List<User> users= userService.listUsers();
        log.info("listUsers:",users.size());
        log.info("one:",users.get(0));
    }



}