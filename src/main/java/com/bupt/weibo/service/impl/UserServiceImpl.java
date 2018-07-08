package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.repository.UserRepository;
import com.bupt.weibo.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    public User getUser(Integer uid) {
        User user=userRepository.findById(uid).orElse(null);
        //log.info("user"+user.getNickname());
        return user;
    }


    @Override
    public User registerUser(UserDTO userDTO) {
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return userRepository.save(user);
    }
}
