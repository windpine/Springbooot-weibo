package com.bupt.weibo.service;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface UserService {

    User getUser(Integer uid);

     public User registerUser(UserDTO userDTO);

    List<User> getUsers();
}
