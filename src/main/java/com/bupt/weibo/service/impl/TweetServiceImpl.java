package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.TweetDTO;
import com.bupt.weibo.dto.mapper.TweetMapper;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
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
        return tweetRepository.findAll();
    }

    @Override
    public List<Tweet> getPersonalTweets(Integer UID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findTweetsByUid(UID,sort);
    }

    @Override
    public Boolean publishTweet(TweetDTO tweetDTO) {
        //转换dtoToEntity
        Tweet tweet = tweetMapper.convertToEntity(tweetDTO);
        tweet.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        Tweet saveTweet = tweetRepository.saveAndFlush(tweet);
        if(saveTweet.equals(tweet))
            return true;
        else
            return false;
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
