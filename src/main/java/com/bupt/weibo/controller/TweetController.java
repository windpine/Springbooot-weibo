package com.bupt.weibo.controller;

import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.service.TweetService;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
