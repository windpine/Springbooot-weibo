package com.bupt.weibo.service;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
public interface TweetService {

    void deleteATweet(int TID) throws Exception;

    void likeATweet(int TID) throws Exception;
}
