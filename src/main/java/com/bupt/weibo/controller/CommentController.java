package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.service.CommentService;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //获取某一微博的所有评论
    @GetMapping("/{TID}")
    public ResultDTO getTweetComments(@PathVariable(name="TID") Integer TID){
        List<Comment> comments = commentService.getTweetComments(TID);
        if(comments.size() != 0){
            return ResultUtils.onSuccess(JSON.toJSONString(comments));
        }
        else{
            return ResultUtils.onError("no comment");
        }
    }
    //对一条微博评论
    @PostMapping("/")
    public ResultDTO sendAComment(@RequestBody CommentDTO commentDTO) throws Exception{
        Comment comment = commentService.commentATweet(commentDTO);

        Map result = new HashMap<String,Integer>();
        result.put("CID",comment.getCid());
        //实际上不需要这么完整的数据，只需id即可
        return ResultUtils.onSuccess(JSONObject.toJSONString(result));
    }

    //删除一条评论
    @DeleteMapping("/{CID}")
    public ResultDTO deleteATweet(@PathVariable(name = "CID") int CID)throws Exception{

        commentService.deleteAComment(CID);
        return ResultUtils.onSuccess();
    }


}
