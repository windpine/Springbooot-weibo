package com.bupt.weibo.service;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
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

    User getUserById(String uid);

    User registerUser(UserDTO userDTO) throws DisabledAccountException;

     User getUserByEmail(String email);

     User getUserByName(String username);

    List<User> listUsers();
    User updateRolesById(String id, List<Role> roles);
    User updatePermissionsById(String id, List<Permission> permissions);
    void delUserById(String id);

}
