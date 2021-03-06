package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.TweetGetDTO;
import com.bupt.weibo.dto.TweetPostDTO;
import com.bupt.weibo.entity.Tweet;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.TweetService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
//查询tweet失败或tweet不存在时返回异常'no tweet or error'
@RestController
@RequestMapping(value = TweetController.PATH)
public class TweetController {
    public static final String PATH = "/tweets";
    public static final String REPOSTPATH="/repost/{TID}";
    public static final String UIDPATH="/{UID}";
    public static final String GETOPICTITLE = "/topic/{topicTitle}";
    public static final String TIDPATH = "/{TID}";
    public static final String LIKESPATH = "/likes";
    //创建日志记录
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //常用日志语句
    private static final String MAP_SUCCCESS = TweetController.class.toString() + ":" +"Map 包装成功";
    @Autowired
    private TweetService tweetService;
    @GetMapping
    //获取所有微博
    public ResponseEntity<ResultDTO> getAllTweet(UriComponentsBuilder uriComponentsBuilder){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        List<List<TweetGetDTO>> tweets = tweetService.getAllTweets();
        //JSONArray mapJSON=(com.alibaba.fastjson.JSONArray) JSON.toJSON(tweets);
        Map<String,List<List<TweetGetDTO>>> result = new HashMap<>();
        logger.info(MAP_SUCCCESS);
        if(tweets.size() != 0){
            result.put("tweetList",tweets);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no tweet or error");
        }
    }
    //获取某一微博的转发微博列表
    @GetMapping(value = REPOSTPATH)
    public ResponseEntity<ResultDTO> getRepostTweets(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name="TID") int TID){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/repost/"+TID);
        List<TweetGetDTO> reposts=tweetService.getRepostTweets(TID);
        Map<String,List<TweetGetDTO>> result=new HashMap<>();
        logger.info(MAP_SUCCCESS);
        if(reposts.size()!=0){
            result.put("repostList",reposts);
            return new ResponseEntity<>(ResultUtils.onSuccess(result),headers,HttpStatus.OK);
        }
        else{
            throw new ResultException("no repost or error");
        }
    }

    //获取某一用户发表的微博
    @GetMapping(value = UIDPATH)
    public ResponseEntity<ResultDTO> getPersonalTweet(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "UID") String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+UID);
        //map tweet
        List<List<TweetGetDTO>> tweets = tweetService.getPersonalTweets(UID);
        Map<String,List<List<TweetGetDTO>>> result = new HashMap<>();
        //返回结果
        if(tweets.size() != 0){
            result.put("tweetList",tweets);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no tweet or error");
        }
    }

    //发表一条微博,使用json传递TweetDTO
    @PostMapping
    public ResponseEntity<Tweet> publishTweet(UriComponentsBuilder uriComponentsBuilder, @RequestBody TweetPostDTO tweetPostDTO){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        //返回发表结果
        if(tweetPostDTO != null){
            Tweet saveTweet=tweetService.publishTweet(tweetPostDTO);
            if(saveTweet!=null){
                return new ResponseEntity<>(saveTweet,headers,HttpStatus.OK);
            }else{
                throw new ResultException("save fail");
            }
        }else{
            throw new ResultException("get tweet fail");
        }
    }
    //查看某一话题的微博，路径修改为/tweets/topic/{topicTitle}
    @GetMapping(value=GETOPICTITLE)
    public ResponseEntity<ResultDTO> getTopicTitle(UriComponentsBuilder uriComponentsBuilder, @PathVariable(name="topicTitle") String topicTitle){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/topic/"+topicTitle);
        //map tweet
        List<Tweet> tweets = tweetService.getTopicTweets(topicTitle);
        Map<String,List<Tweet>> result = new HashMap<String,List<Tweet>>();
        //返回result
        if(tweets.size() != 0){
            result.put("tweetList",tweets);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(tweets),headers,HttpStatus.OK);
        }else{
            throw new ResultException("no tweet or error");
        }
    }

    //删除一条微博
    @DeleteMapping(value = TIDPATH)
    public ResponseEntity<ResultDTO> deleteATweet(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "TID") int TID)throws Exception{
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+TID);
        //返回
        tweetService.deleteATweet(TID);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
    }

    //点赞某条微博
    @PutMapping(value = LIKESPATH+TIDPATH)
    public ResponseEntity<ResultDTO> likeATweet(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "TID") int TID) throws Exception{
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+LIKESPATH+"/"+TID);
        //返回
        tweetService.likeATweet(TID);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
    }
}
