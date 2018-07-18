package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.dto.mapper.UserInfoMapper;
import com.bupt.weibo.dto.mapper.UserMapper;
import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.UserInfo;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.repository.UserInfoRepository;
import com.bupt.weibo.repository.UserRepository;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.UUIDUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

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

    @Autowired
    UserInfoRepository userInfoRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserMapper userMapper;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public User getUserById(String uid) {
        User user=userRepository.findById(uid).orElse(null);
        //log.info("user"+user.getNickname());
        return user;
    }


    @Override
    public User registerUser(UserDTO userDTO) throws DisabledAccountException{
        log.info("Mapper转换UserDTO->User");
        User user=userMapper.convertToEntity(userDTO);
        UserInfoDTO userInfoDTO=new UserInfoDTO();

        userInfoDTO.setSex("无");
        if(userDTO.getAvatarUrl()==""){
            userInfoDTO.setAvatarUrl("https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png");
        }else{
            userInfoDTO.setAvatarUrl(userDTO.getAvatarUrl());
        }



        //生成UUID
        String uid=UUIDUtils.getOneUUID();
        user.setUid(uid);
        userInfoDTO.setUid(uid);
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */

        String newPs = new SimpleHash("MD5",
                user.getPassword(), salt, 1024).toHex();
        user.setPassword(newPs);

        UserInfo userInfo=userInfoMapper.convertToEntity(userInfoDTO);

        //检查用户是否存在
        if(userRepository.findByUsername(user.getUsername())==null){
            User _user;
            log.info("成功注册！");
            try{
                userInfoRepository.save(userInfo);
                _user=userRepository.save(user);
            }catch (ResultException e){
                _user=null;
            }
            return _user;
        }else {
            throw new ResultException("已存在该用户名");
        }
    }


    @Override
    public String checkOldPassword(String uid,String password){
        User user= userRepository.findByUid(uid);
//        user.setUid(UUIDUtils.getOneUUID());
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */

        String newPs = new SimpleHash("MD5",
                password, salt, 1024).toHex();
        log.info("newPS:",newPs);
        return newPs;
    }

    @Override
    public UserInfoDTO getUserInfoByUid(String uid){
        UserInfo userInfo=userInfoRepository.findByUid(uid);
        User user=userRepository.findByUid(uid);
        UserInfoDTO userInfoDTO;
        if(userInfo==null){
            userInfoDTO=new UserInfoDTO(user.getUid(),
                    user.getNickname(),user.getUsername(),0,
                    0,0,"",
                    "无",user.getPassword(),user.getEmail());
        }else{
            userInfoDTO=new UserInfoDTO(user.getUid(),
                    user.getNickname(),user.getUsername(),userInfo.getTweets(),
                    userInfo.getFollows(),userInfo.getFollowers(),userInfo.getAvatarUrl(),
                    userInfo.getSex(),user.getPassword(),user.getEmail());
        }
        return userInfoDTO;
    }

    @Override
    public void updateUser(UserInfoDTO userInfoDTO) throws DisabledAccountException{
        log.info("Mapper转换UserInfoDTO->UserInfo");
        UserDTO userDTO=new UserDTO();
        userDTO.setUid(userInfoDTO.getUid());
        userDTO.setNickname(userInfoDTO.getNickname());
        userDTO.setUsername(userInfoDTO.getUsername());
        userDTO.setEmail(userInfoDTO.getEmail());
        userDTO.setPassword(userInfoDTO.getPassword());

        //UserInfoDTO newUserInfoDTO=new UserInfoDTO();


        UserInfo userInfo=userInfoMapper.convertToEntity(userInfoDTO);
        User user=userInfoMapper.convertToEntity(userDTO);
        //User user=userMapper.convertToEntity(userDTO);
        //生成UUID
        //userInfo.setUid(UUIDUtils.getOneUUID());//获取UID
        //user.setUid(UUIDUtils.getOneUUID());//获取UID
        //todo:salt配置
//        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
//        /*
//         * MD5加密：
//         * 使用SimpleHash类对原始密码进行加密。
//         * 第一个参数代表使用MD5方式加密
//         * 第二个参数为原始密码
//         * 第三个参数为盐值，即用户名
//         * 第四个参数为加密次数
//         * 最后用toHex()方法将加密后的密码转成String
//         * */
//
//        String newPs = new SimpleHash("MD5",
//                user.getPassword(), salt, 1024).toHex();
//        user.setPassword();
        //检查用户是否存在
        if(userRepository.findByUsername(user.getUsername()) != null){
            log.info("成功更新！");
            //todo：test
            log.info("user:",userRepository.findByUsername(user.getUsername()).getEmail());
            userRepository.updateUser(user.getUid(),user.getNickname(),user.getPassword(),user.getEmail());
            //userInfoRepository.updateUserInfo(userInfo.getUid(),userInfo.getSex(),userInfo.getFollows(),userInfo.getFollowers(),userInfo.getAvatarUrl());
            if(userInfoRepository.findByUid(userInfo.getUid()) != null){
                log.info("userInfo:",userInfoRepository.findByUid(userInfo.getUid()).getSex());
                userInfoRepository.updateUserInfo(userInfo.getUid(),userInfo.getSex(),userInfo.getFollows(),userInfo.getFollowers(),userInfo.getAvatarUrl());
            }else{
                log.info("新添加UserInfo！");
                log.info("UserInfo:",userInfo.getSex());
                userInfoRepository.save(userInfo);
            }
        }else {
            throw new DisabledAccountException("不存在该用户");
        }
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByName(String username) {

        return userRepository.findByUsername(username);
    }
    @Override
    public  User getUserByNickname(String nickname ){
        return userRepository.findByNickname(nickname);
    }

    @Override
    public List<User> listUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User updateRolesById(String id, List<Role> roles) {
        if(!userRepository.existsById(id)){
            throw new ResultException();
        }
        try {
            User user = userRepository.findById(id).orElse(null);
            user.setRoles(roles);
            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            //throw e;
            throw new ResultException();
        }
    }

    @Override
    public User updatePermissionsById(String id, List<Permission> permissions) {
        if(!userRepository.existsById(id)){
            throw new ResultException();
        }
        try {
            User user = userRepository.findById(id).orElse(null);
            user.setPermissions(permissions);
            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public void delUserById(String id) {
        userRepository.deleteById(id);
    }
}
