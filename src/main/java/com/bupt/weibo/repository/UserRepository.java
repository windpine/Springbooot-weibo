package com.bupt.weibo.repository;

import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String> {

    //User findByNameAndPassword(String name, String password);
    User findByUid(String uid);


    List<User> findAll();

    @Transactional
    User findByUsername(String username);

    User findByEmail(String email);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User u set u.uid=?1,u.nickname=?2,u.password=?3,u.email=?4 where u.uid=?1")
    void updateUser(String uid, String nickname, String password, String email);


}
