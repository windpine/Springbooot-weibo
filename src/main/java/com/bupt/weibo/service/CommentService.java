package com.bupt.weibo.service;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface CommentService {

    Comment commentATweet(CommentDTO commentDTO);

    void deleteAComment(int CID);
}
