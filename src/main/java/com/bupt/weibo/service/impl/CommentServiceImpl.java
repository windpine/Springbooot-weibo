package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.repository.CommentRepository;
import com.bupt.weibo.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void deleteAComment(int CID) {
        commentRepository.deleteById(CID);
    }

    @Override
    public List<Comment> getTweetComments(Integer TID) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findCommentsByTid(TID,sort);
    }
}
