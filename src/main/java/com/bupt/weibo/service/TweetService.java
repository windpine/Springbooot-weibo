package com.bupt.weibo.service;


import com.bupt.weibo.dto.PostTweetDTO;
import com.bupt.weibo.entity.Tweet;

import java.util.List;

/**
 * 有关微博的服务
 */

public interface TweetService {
    //获取所有微博
    List<Tweet> getAllTweets();
    //获取某一用户发表的微博
    List<Tweet> getPersonalTweets(String UID);
    //发表一条微博
    Tweet publishTweet(PostTweetDTO postTweetDTO);
    //查看某一话题的微博
    List<Tweet> getTopicTweets(String topicTitle);


    void deleteATweet(int TID) throws Exception;

    void likeATweet(int TID) throws Exception;

}
