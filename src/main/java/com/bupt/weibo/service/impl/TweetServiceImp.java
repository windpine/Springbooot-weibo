package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.repository.CommentRepository;
import com.bupt.weibo.repository.TopicRepository;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

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
    public void publishTweet(String content,Integer UID,String topicTitle) {
        Tweet newTweet = new Tweet();
        newTweet.setContent(content);
        newTweet.setUid(UID);
        newTweet.setCreateTime(new Timestamp(System.currentTimeMillis()));
        newTweet.setTopicTitle(topicTitle);
        tweetRepository.saveAndFlush(newTweet);
    }

    @Override
    public List<Tweet> getTopicTweets(String topicTitle) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findTweetsByTopicTitle(topicTitle,sort);
    }

    @Override
    public List<Comment> getTweetComments(Integer TID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findCommentsByTID(TID,sort);
    }
}
