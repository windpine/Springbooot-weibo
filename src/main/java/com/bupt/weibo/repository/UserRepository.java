package com.bupt.weibo.repository;

import com.bupt.weibo.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
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
    User findByUid(Integer uid);


    List<User> findAll();

    @Transactional
    User findByUsername(String username);

    User findByEmail(String email);
}
