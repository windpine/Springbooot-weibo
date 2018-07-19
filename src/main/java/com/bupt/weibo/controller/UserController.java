package com.bupt.weibo.controller;

import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.UserInfoDTO;
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

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

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
    public static final String PWDPATH="/{UID}/{password}";
    public static final String USERNAMEPATH="/username/{USERNAME}";

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
        UserInfoDTO userInfoDTO = userService.getUserInfoByUid(UID);
        Map<String, UserInfoDTO> result = new HashMap<String,UserInfoDTO>();
        //返回结果
        if(userInfoDTO != null){
            result.put("user",userInfoDTO);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no such a user!");
        }
    }


    //检测用户更改密码时原密码是否匹配
    @GetMapping(value=PWDPATH)
    public ResponseEntity<ResultDTO> checkOldPassword(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "UID") String UID,@PathVariable(name = "password") String password){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+UID+"/"+password);
        String newPs=userService.checkOldPassword(UID,password);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(newPs),headers, HttpStatus.OK);

    }

    //获得所有用户
    @GetMapping
    public ResponseEntity<ResultDTO> getUsers(UriComponentsBuilder uriComponentsBuilder){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH);
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

    //更新用户
    @PutMapping(value=UIDPATH)
    public ResponseEntity<ResultDTO> updateUser(@RequestBody UserInfoDTO userInfoDTO,
                                                   UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "UID") String UID) throws Exception{
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+UID);
        if(userInfoDTO != null){
            logger.info("=======开始更新用户=========");
//            logger.info("username:"+ userDTO.getUsername()
//                    +" password: "+userDTO.getPassword());
            userService.updateUser(userInfoDTO);
//            UserInfoDTO userInfo=userInfoDTO;
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("更新用户失败！");
        }
    }

    //根据用户名字获得ID
    @GetMapping(value = USERNAMEPATH)
    public ResponseEntity<ResultDTO> getUserByUsername(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "USERNAME") String USERNAME){
        //包装header
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/username/"+USERNAME);
        User user=userService.getUserByUsername(USERNAME);
        Map<String,String> result=new HashMap<>();
        if(user!=null){
            result.put("uid",user.getUid());
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers, HttpStatus.OK);
        }else{
            throw new ResultException("no user or error");
        }
    }

}
