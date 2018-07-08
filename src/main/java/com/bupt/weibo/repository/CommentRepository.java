package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Transactional
    List<Comment> findCommentsByTID(Integer TID, Sort sort);
}
