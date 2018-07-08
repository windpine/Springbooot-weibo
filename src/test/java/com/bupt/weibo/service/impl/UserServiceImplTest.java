package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        User user=userService.getUser(1);
        log.info("user:"+user.getNickname());
        Assert.assertNotNull(user);
    }
}