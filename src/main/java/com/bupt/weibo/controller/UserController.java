package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.UserInfo;
import com.bupt.weibo.entity.enums.ErrorCode;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RestController
@RequestMapping(value = UserController.PATH)
public class UserController {
    public static final String PATH = "/users";
    public static final String UIDPATH="/{UID}";
    public static final String UPDATEPATH="/{UID}/update";

    //创建日志记录
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //常用日志语句
    private static final String MAP_SUCCCESS = TweetController.class.toString() + ":" +"Map 包装成功";

    @Autowired
    UserService userService;

    @Autowired
    ResultUtils resultUtils;


    //根据用户ID获得一个用户的信息
    @GetMapping(value=UIDPATH)
    public ResponseEntity<ResultDTO> getUser(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "UID") String UID){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+UID);
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");
        //map tweet
        User user = userService.getUserById(UID);
        Map<String, User> result = new HashMap<String,User>();
        //返回结果
        if(user != null){
            result.put("user",user);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no such a user!");
        }
    }

    //获得所有用户
    @GetMapping
    public ResponseEntity<ResultDTO> getUsers(UriComponentsBuilder uriComponentsBuilder){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
//        headers.setAccessControlAllowCredentials(true);
//        headers.setAccessControlAllowOrigin("*");
        List<User> users = userService.listUsers();
        Map<String,List<User>> result = new HashMap<String,List<User>>();
        logger.info(MAP_SUCCCESS);
        if(users.size() != 0){
            result.put("userList",users);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no users or error");
        }
    }

//    //更新用户
//    @PostMapping(value=UPDATEPATH)
//    public ResponseEntity<UserInfoDTO> updateUser(@RequestBody UserInfoDTO userInfoDTO,
//                                                   UriComponentsBuilder uriComponentsBuilder) throws Exception{
//        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,UPDATEPATH);
//        headers.setAccessControlAllowOrigin("*");
//        headers.setAccessControlAllowCredentials(true);
//        if(userInfoDTO != null){
//            logger.info("=======开始更新用户=========");
////            logger.info("username:"+ userDTO.getUsername()
////                    +" password: "+userDTO.getPassword());
//            userService.updateUser(userInfoDTO);
//            UserInfoDTO userInfo=userInfoDTO;
//            return new ResponseEntity<UserInfoDTO>(user,headers,HttpStatus.OK);
//        }else{
//            throw new ResultException("Username:"+userInfoDTO.get, ErrorCode.ERROR);
//        }
//    }



}
