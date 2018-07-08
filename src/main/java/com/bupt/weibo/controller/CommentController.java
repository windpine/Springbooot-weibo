package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/")
    public String sendAComment(@RequestBody CommentDTO commentDTO) throws Exception{
        Comment comment = commentService.commentATweet(commentDTO);

        //实际上不需要这么完整的数据，只需id即可
        return JSONObject.toJSONString(comment);
    }

}
