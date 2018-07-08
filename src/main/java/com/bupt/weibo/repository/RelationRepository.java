package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;
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
public interface RelationRepository extends JpaRepository<Relation,RelationPK> {

    @Transactional
    @Modifying
    @Query("SELECT u From User u inner join Relation r on u.uid=r.followerId where r.followId=?1")
    List<User> findByFollowId(int uid);


    @Transactional
    @Modifying
    @Query("SELECT u From User u inner join Relation r on u.uid=r.followId where r.followerId=?1")
    List<User> findByFollowerId(int uid);
}
