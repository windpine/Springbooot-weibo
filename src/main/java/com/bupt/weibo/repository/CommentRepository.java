package com.bupt.weibo.repository;

import com.bupt.weibo.dto.CommentGetDTO;
import com.bupt.weibo.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Transactional
    @Modifying
    @Query("select new com.bupt.weibo.dto.CommentGetDTO(c,u.nickname,info.avatarUrl) From Comment c inner join  User u on c.uid=u.uid left join UserInfo info on u.uid=info.uid where c.tid=?1 order by c.createTime desc ")
    List<CommentGetDTO> findCommentsByTid(Integer Tid);

    @Transactional
    Comment findCommentByCid(Integer Cid);
}
