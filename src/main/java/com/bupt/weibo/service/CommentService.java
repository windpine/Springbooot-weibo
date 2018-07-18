package com.bupt.weibo.service;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.dto.CommentGetDTO;
import com.bupt.weibo.entity.Comment;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface CommentService {

    Comment commentATweet(CommentDTO commentDTO)  throws Exception ;

    void deleteAComment(int CID);
    //获取某一微博的所有评论
    List<CommentGetDTO> getTweetComments(Integer TID);
}
