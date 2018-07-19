package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;
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
@Transactional
public interface RelationRepository extends JpaRepository<Relation,RelationPK> {

    List<Relation> findAll();

    @Transactional
    @Modifying
    @Query(value="select r from Relation r where r.followId=?1 and r.followerId=?2")
    List<Relation> findById(String followId,String followerId);

    @Transactional
    @Modifying
    @Query("select u from User u inner join Relation r on u.uid=r.followerId where r.followId=?1 ")
    List<User> findFollowersByFollowId(String uid);


    @Transactional
    @Modifying
    @Query(value="select u from User u inner join Relation r on u.uid=r.followId where r.followerId=?1 ")
    List<User> findFollowsByFollowerId(String uid);//返回的是followerId=uid的所有对应的followList
    //根据UID查找关联的Tweets
    @Query("select r,t,u from Tweet t  inner join Relation r on r.followerId = t.uid left join User u on r.followerId = u.uid where r.followId = ?1 ")
    @Transactional
    List<Object[]> findRelationTweet(String UID);

}

