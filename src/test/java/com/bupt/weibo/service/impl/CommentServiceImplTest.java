package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;
import com.bupt.weibo.service.CommentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Test
    public void commentATweet() {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setContent("测试");
        commentDTO.setSrcId(-1);
        commentDTO.setTid(1);
        commentDTO.setUid("uljlkjlkjlkjlkj");
        Comment comment = commentService.commentATweet(commentDTO);

        Assert.assertNotNull(comment);

    }

    @Test
    public void deleteComment() throws Exception{
        commentService.deleteAComment(1);
    }
}