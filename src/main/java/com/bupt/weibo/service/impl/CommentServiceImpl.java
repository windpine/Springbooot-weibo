package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.dto.CommentGetDTO;
import com.bupt.weibo.dto.mapper.CommentMapper;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.repository.CommentRepository;
import com.bupt.weibo.service.CommentService;
import com.bupt.weibo.service.TweetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TweetService tweetService;

    @Override
    @Transactional
    public Comment commentATweet(CommentDTO commentDTO) throws Exception {

        Comment comment = commentMapper.convertToEntity(commentDTO);
        Comment saveComment=commentRepository.save(comment);
        if(saveComment!=null){
            tweetService.AddAComment(saveComment.getTid());
            return saveComment;
        }
        else
            return null;
    }

    @Override
    public void deleteAComment(Integer CID) throws Exception{
        Comment comment=commentRepository.findCommentByCid(CID);
        commentRepository.deleteById(CID);
        tweetService.SubAComment(comment.getTid());

    }

    @Override
    public List<CommentGetDTO> getTweetComments(Integer TID){
        //Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findCommentsByTid(TID);
    }
}
