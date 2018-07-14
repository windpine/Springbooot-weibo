package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.CommentService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
//查询无comment，或查询失败抛出异常'no comment'
@RestController
@RequestMapping(value = CommentController.PATH)
public class CommentController {
    public static final String PATH = "/comments";
    public static final String TIDPATH = "/{TID}";
    public static final String CIDPATH = "/{CID}";
    @Autowired
    CommentService commentService;
    //获取某一微博的所有评论
    @GetMapping(value = TIDPATH)
    public ResponseEntity<ResultDTO> getTweetComments(UriComponentsBuilder uriComponentsBuilder, @PathVariable(name="TID") Integer TID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+TID);
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");
        //map comments
        List<Comment> comments = commentService.getTweetComments(TID);
        Map result = new HashMap<String, List<Comment>>();
        //返回
        if(comments.size() != 0){
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }
        else{
            throw new ResultException("no comments or error");
        }
    }
    //对一条微博评论
    @PostMapping
    public ResponseEntity<ResultDTO> sendAComment(UriComponentsBuilder uriComponentsBuilder,@RequestBody CommentDTO commentDTO) throws Exception{
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");

        Comment comment = commentService.commentATweet(commentDTO);
        Map result = new HashMap<String,Integer>();
        //实际上不需要这么完整的数据，只需id即可
        result.put("CID",comment.getCid());
        //返回
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers,HttpStatus.OK);
    }

    //删除一条评论
    @DeleteMapping(value = CIDPATH)
    public ResponseEntity<ResultDTO> deleteATweet(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "CID") int CID)throws Exception{
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+CID);
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");

        commentService.deleteAComment(CID);
        //返回
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
    }


}
