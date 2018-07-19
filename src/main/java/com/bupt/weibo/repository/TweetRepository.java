package com.bupt.weibo.repository;

import com.bupt.weibo.dto.TweetGetDTO;
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
    @Modifying
    @Query("select new com.bupt.weibo.dto.TweetGetDTO(t,u.nickname,info.avatarUrl) From Tweet t inner join  User u on t.uid=u.uid left join UserInfo info on u.uid=info.uid order by t.createTime DESC")
    List<TweetGetDTO> findAllTweet();

    @Transactional
    List<Tweet> findTweetsByTopicTitle(String topicTitle,Sort sort);


    @Transactional
    @Modifying
    @Query("update Tweet t set t.likes = t.likes+1 where t.tid=?1")
    void updateByStar(int TID);

    @Transactional
    @Modifying
    @Query("update Tweet t set t.forwards=t.forwards+1 where t.tid=?1")
    void updateForwards(int TID);

    @Transactional
    @Modifying
    @Query("update Tweet t set t.comments = t.comments+1 where t.tid=?1")
    void updateComment(int TID);

    @Transactional
    @Modifying
    @Query("select new com.bupt.weibo.dto.TweetGetDTO(t,u.nickname,info.avatarUrl) From Tweet t inner join  User u on t.uid=u.uid left join UserInfo info on u.uid=info.uid where t.srcId=?1 order by t.createTime DESC")
    List<TweetGetDTO> findTweetsBySrcId(int srcId);

    @Transactional
    @Query("select new com.bupt.weibo.dto.TweetGetDTO(t,u.nickname,info.avatarUrl) From Tweet t inner join  User u on t.uid=u.uid left join UserInfo info on u.uid=info.uid where t.tid=?1")
    TweetGetDTO findATweetBySrcId(int srcId);


}
