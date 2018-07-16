package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Transactional
    List<Message> findAllByUid(String UID);
    @Query("select m,me,t,u from Message m,Mention me,Tweet t,User u where m.uid = me.uid and m.uid = ?1 and t.tid = me.tid and u.uid = t.uid and m.type= 0")
    @Transactional
    List<Object[]> findAllMessageAndTweetJoin(String Uid);
    @Query("select m,c,u from Message m,Comment c,User u where m.srcId = c.cid and m.uid = ?1 and u.uid= c.uid and m.type =1")
    @Transactional
    List<Object[]> findAllMessageAndCommentJoin(String Uid);
    @Transactional
    @Query("select m,t,u from Message m,Tweet t,User u where m.srcId = t.tid and m.srcUid = u.uid and m.uid =?1 and m.type=2")
    List<Object[]> findAllLikesMessage(String Uid);


}
