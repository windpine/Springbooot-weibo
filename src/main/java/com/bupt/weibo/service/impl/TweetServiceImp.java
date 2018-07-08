package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.TweetDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.repository.CommentRepository;
import com.bupt.weibo.repository.TopicRepository;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
@Service("TweetService")
public class TweetServiceImp implements TweetService {
    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TopicRepository topicRepository;

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
        Tweet newTweet = new Tweet();
        newTweet.setContent(tweetDTO.getContent());
        newTweet.setUid(tweetDTO.getUid());
        newTweet.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        Tweet saveTweet = tweetRepository.saveAndFlush(newTweet);
        if(saveTweet.equals(newTweet))
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
    public List<Comment> getTweetComments(Integer TID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findCommentsByTid(TID,sort);
    }
}
