package com.bupt.weibo.service;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface UserService {

    User getUser(Integer uid);

    List<User> getUsers();

     public User registerUser(UserDTO userDTO) throws DisabledAccountException;

     User getUserByEmail(String email);

     User getUserByName(String username);
}
