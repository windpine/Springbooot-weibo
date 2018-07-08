package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.TweetDTO;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.service.TweetService;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@RestController
@RequestMapping("/tweets")
public class TweetController {
    @Autowired
    TweetService tweetService;
    @GetMapping
    //获取所有微博
    public  ResultDTO getAllTweet(){
        List<Tweet> tweets = tweetService.getAllTweets();
        if(tweets.size() != 0){
            return ResultUtils.onSuccess(JSON.toJSONString(tweets));
        }else{
            return ResultUtils.onError("no tweet");
        }
    }
    //获取某一用户发表的微博
    @GetMapping("/{UID}")
    public ResultDTO getPersonalTweet(@PathVariable(name = "UID") Integer UID){
        List<Tweet> tweets = tweetService.getPersonalTweets(UID);
        if(tweets.size() != 0){
            return ResultUtils.onSuccess(JSON.toJSONString(tweets));
        }else{
            return ResultUtils.onError("no tweet");
        }
    }
    //发表一条微博,使用json传递TweetDTO
    @PostMapping
    public ResultDTO publishTweet(@RequestBody TweetDTO tweetDTO){
        if(tweetDTO != null){
            if(tweetService.publishTweet(tweetDTO)){
                return ResultUtils.onSuccess();
            }else{
                return ResultUtils.onError("save fail");
            }
        }else{
            return ResultUtils.onError("get tweet fail");
        }
    }
    //查看某一话题的微博，路径修改为/tweets/topic/{topicTitle}
    @GetMapping("/topic/{topicTitle}")
    public ResultDTO getTopicTitle(@PathVariable(name="topicTitle") String topicTitle){
        List<Tweet> tweets = tweetService.getTopicTweets(topicTitle);
        if(tweets.size() != 0){
            return ResultUtils.onSuccess(JSON.toJSONString(tweets));
        }else{
            return ResultUtils.onError("no tweet");
        }
    }

    //删除一条微博
    @DeleteMapping("/{TID}")
    public ResultDTO deleteATweet(@PathVariable(name = "TID") int TID)throws Exception{

        tweetService.deleteATweet(TID);
        return ResultUtils.onSuccess();
    }

    //点赞某条微博
    @PutMapping("/likes/{TID}")
    public ResultDTO likeATweet(@PathVariable(name = "TID") int TID) throws Exception{

        tweetService.likeATweet(TID);

        return ResultUtils.onSuccess();
    }
}
