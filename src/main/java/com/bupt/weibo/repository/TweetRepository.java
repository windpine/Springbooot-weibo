package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Tweet;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    @Transactional
    List<Tweet> findTweetsByUid(Integer UID, Sort sort);

    @Transactional
    List<Tweet> findTweetsByTopicTitle(String topicTitle,Sort sort);


}
