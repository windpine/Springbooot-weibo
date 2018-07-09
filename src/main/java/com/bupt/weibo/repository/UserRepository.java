package com.bupt.weibo.repository;

import com.bupt.weibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //User findByNameAndPassword(String name, String password);
    User findByUid(Integer uid);

    User findByEmail(String email);

    User findByUsername(String username);



}
