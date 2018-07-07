package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
