package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.repository.CommentRepository;
import com.bupt.weibo.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment commentATweet(CommentDTO commentDTO) {

        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);

        return commentRepository.save(comment);
    }
}
