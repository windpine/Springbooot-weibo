package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.User;
import com.bupt.weibo.repository.UserRepository;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public User getUser() {
        User user=userRepository.findById(1).orElse(null);
        log.info("user"+user.getNickname());
        return user;
    }
}
