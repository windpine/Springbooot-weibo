package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.UserDTO;
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

    @Test
    public void getUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("tsseagull_gd@163.com");
        userDTO.setUsername("tanshangou");
        userDTO.setNickname("doggy");
        userDTO.setPassword("kk6665596");
        userDTO.setSex("male");
        userService.registerUser(userDTO);
    }
}