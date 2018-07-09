package com.bupt.weibo.service.impl;

import com.bupt.weibo.service.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TweetServiceImplTest {

    @Autowired
    TweetService tweetService;

    @Test
    public void deleteATweet() throws Exception {
        tweetService.deleteATweet(1);
    }

    @Test
    public void likeATweet() throws Exception{
        tweetService.likeATweet(1);
    }
}