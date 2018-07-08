package com.bupt.weibo.service.impl;

import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {
    @Autowired
    TweetRepository tweetRepository;

    @Override
    public void deleteATweet(int TID) throws Exception{
        tweetRepository.deleteById(TID);
    }

    @Override
    public void likeATweet(int TID) throws Exception {
        tweetRepository.updateByStar(TID);
    }
}
