package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Tweet;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Repository
public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    @Transactional
    List<Tweet> findTweetsByUid(String UID, Sort sort);

    @Transactional
    List<Tweet> findTweetsByTopicTitle(String topicTitle,Sort sort);


    @Transactional
    @Modifying
    @Query("update Tweet t set t.likes = t.likes+1 where t.tid=?1")
    void updateByStar(int TID);

}
