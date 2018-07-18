package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.TweetGetDTO;
import com.bupt.weibo.dto.TweetPostDTO;
import com.bupt.weibo.dto.mapper.TweetMapper;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.repository.TweetRepository;
import com.bupt.weibo.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<List<TweetGetDTO>> getAllTweets() {
        //Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        List<TweetGetDTO> tweetList =tweetRepository.findAllTweet();
        List<List<TweetGetDTO>> newTweetList=new ArrayList<>();
        for(int i=0;i<tweetList.size();i++){
            List<TweetGetDTO> tLink=new LinkedList<>();
            tLink.add(tweetList.get(i));
            TweetGetDTO tgDTO=tweetList.get(i);
            while(tgDTO.getSrcId()>0){
                tgDTO=tweetRepository.findATweetBySrcId(tgDTO.getSrcId());
                if(tgDTO!=null)
                    tLink.add(tgDTO);
            }
            newTweetList.add(tLink);
        }
        return newTweetList;
    }

    @Override
    public List<TweetGetDTO> getRepostTweets(int TID){
        return tweetRepository.findTweetsBySrcId(TID);
    }

    @Override
    public List<Tweet> getPersonalTweets(String UID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return tweetRepository.findTweetsByUid(UID,sort);
    }

    @Transactional
    @Override
    public Tweet publishTweet(TweetPostDTO tweetPostDTO) {
        //转换dtoToEntity
        Tweet tweet = tweetMapper.convertToEntity(tweetPostDTO);
        Tweet saveTweet = tweetRepository.saveAndFlush(tweet);
        if(saveTweet.equals(tweet)){
            if(saveTweet.getSrcId()>=0){
                //给转发数目加1
                tweetRepository.updateForwards(saveTweet.getTid());
            }
            return saveTweet;
        }
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

    @Override
    public void AddAComment(int TID) throws Exception{
        tweetRepository.updateComment(TID);
    }
}
