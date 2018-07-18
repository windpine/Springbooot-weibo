package com.bupt.weibo.service;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import java.util.List;

import com.bupt.weibo.entity.UserInfo;
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

     User getUserByNickname(String nickname );

    List<User> listUsers();
    User updateRolesById(String id, List<Role> roles);
    User updatePermissionsById(String id, List<Permission> permissions);
    void delUserById(String id);
    void updateUser(UserInfoDTO userInfoDTO) throws DisabledAccountException;
    UserInfoDTO getUserInfoByUid(String uid);

    String checkOldPassword(String uid,String password);

}
