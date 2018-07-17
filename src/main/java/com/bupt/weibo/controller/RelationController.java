package com.bupt.weibo.controller;


import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.enums.ErrorCode;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.RelationService;
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

@RestController
@RequestMapping(value=RelationController.PATH)
public class RelationController {
    public static final String PATH="/users";
    public static final String UIDPATH="/{UID}";
    public static final String ADDPATH="/{followerId}/{followId}";
    public static final String DELETEPATH="/{followerId}/{followId}";
    public static final String FOLLOWSPATH="/{uid}/follows";
    public static final String FLOLLOWERSPATH="/{uid}/followers";

    //创建日志记录
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //常用日志语句
    private static final String MAP_SUCCCESS = TweetController.class.toString() + ":" +"Map 包装成功";

    @Autowired
    RelationService relationService;

    @Autowired
    ResultUtils resultUtils;


    //添加一个关系
    @PostMapping(value=ADDPATH)
    public ResponseEntity<ResultDTO> addRelation(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "followId") String followId,@PathVariable(name="followerId") String followerId) throws Exception{
        HttpHeaders headers= ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+followerId+"/"+followId);
        if(relationService.findById(followId,followerId).size()==0){
            logger.info("=======开始添加关系=========");

            relationService.addARelation(followId,followerId);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("添加关系失败，已经关注！");
        }
    }



    //删除一个关系
    @DeleteMapping(value=DELETEPATH)
    public ResponseEntity<ResultDTO> deleteRelation(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "followId") String followId,@PathVariable(name="followerId") String followerId) throws Exception{
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+followerId+"/"+followId);
            logger.info("=======开始删除关系=========");
        if(relationService.findById(followId,followerId).size()!=0){
            relationService.deleteARelation(followId,followerId);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        }else{
            throw new ResultException("无法删除关系，因为没有对应的正确的关系！");
        }
    }


    //根据Id获得粉丝用户列表
    @GetMapping(value=FLOLLOWERSPATH)
    public ResponseEntity<ResultDTO> getAllFollowers(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "uid") String uid) throws Exception{
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+uid+"followers");
        logger.info("=======开始查找粉丝用户=========");
        List<UserInfoDTO> users=relationService.getAllFollowers(uid);
        Map<String,List<UserInfoDTO>> result = new HashMap<String,List<UserInfoDTO>>();
            result.put("userList",users);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result),headers,HttpStatus.OK);
    }

    //根据当前用户ID为Id的关注列表
    @GetMapping(value=FOLLOWSPATH)
    public ResponseEntity<ResultDTO> getAllFollows(UriComponentsBuilder uriComponentsBuilder,@PathVariable(name = "uid") String uid) throws Exception{
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH+"/"+uid+"follows");
        logger.info("=======开始查找关注的用户=========");
        List<UserInfoDTO> users=relationService.getAllFollows(uid);
        Map<String,List<UserInfoDTO>> result = new HashMap<String,List<UserInfoDTO>>();
            result.put("userList",users);
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(result), headers, HttpStatus.OK);
    }

}
