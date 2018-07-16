package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.PostTweetDTO;
import com.bupt.weibo.dto.mapper.TweetMapper;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {
    @Autowired
    TweetMapper tweetMapper;
    @Autowired
    TweetRepository tweetRepository;


    @Override
    public List<Tweet> getAllTweets() {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findAll(sort);
    }

    @Override
    public List<Tweet> getPersonalTweets(String UID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findTweetsByUid(UID,sort);
    }

    @Override
    public Tweet publishTweet(PostTweetDTO postTweetDTO) {
        //转换dtoToEntity
        Tweet tweet = tweetMapper.convertToEntity(postTweetDTO);
        Tweet saveTweet = tweetRepository.saveAndFlush(tweet);
        if(saveTweet.equals(tweet))
            return saveTweet;
        else
            return null;
    }

    @Override
    public List<Tweet> getTopicTweets(String topicTitle) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findTweetsByTopicTitle(topicTitle,sort);
    }


    @Override
    public void deleteATweet(int TID) throws Exception{
        tweetRepository.deleteById(TID);
    }

    @Override
    public void likeATweet(int TID) throws Exception {
        tweetRepository.updateByStar(TID);
    }
}
