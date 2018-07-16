package com.bupt.weibo.repository;

import com.bupt.weibo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {


    UserInfo findByUid(String uid);




    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserInfo u set u.uid=?1,u.sex=?2,u.follows=?3,u.followers=?4,u.avatarUrl=?5 where u.uid=?1")
    void updateUserInfo(String uid, String sex, Integer follows, Integer followers, String avatar);
}
