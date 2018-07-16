package com.bupt.weibo.controller;


import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.enums.ErrorCode;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.RelationService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/users")
public class RelationController {

    @Autowired
    RelationService relationService;


    @RequestMapping("/follow")
    @PostMapping("/")
    public ResponseEntity<ResultDTO> addARelation(Relation relation,UriComponentsBuilder uriComponentsBuilder) throws Exception{
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"/follow");
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");
        if(relationService.addARelation(relation)!=null)
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
        else
            throw new ResultException("Add Relation Fail", ErrorCode.ERROR);
    }

    @RequestMapping(value="/{followId}/follow/{followerId}")
    @DeleteMapping("/")
    public ResponseEntity<ResultDTO> deleteARelation(@PathVariable String followId,@PathVariable String followerId ,UriComponentsBuilder uriComponentsBuilder) throws Exception{
        String subUri="/"+followId+"/follow/"+followerId;
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,subUri);
        relationService.deleteARelation(followId,followerId);
        return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
    }

    @RequestMapping(value = "/{uid}/follows")
    @GetMapping("/")
    public ResponseEntity<ResultDTO> getAllFollows(@PathVariable String uid ,UriComponentsBuilder uriComponentsBuilder) throws Exception{
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"/"+uid+"/follows");
        List<User> users=relationService.getAllFollows(uid);
        if(users!=null)
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(JSONObject.toJSONString(users)),headers,HttpStatus.OK);
        else
            throw new ResultException("No Follows", ErrorCode.NOFOLLOWORFOLLOWER);
    }

    @RequestMapping(value = "/{uid}/followers")
    @GetMapping("/")
    public ResponseEntity<ResultDTO> getAllFollowers(@PathVariable String uid,UriComponentsBuilder uriComponentsBuilder) throws Exception{
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"/"+uid+"/followers");
        List<User> users=relationService.getAllFollowers(uid);
        if(users!=null)
            return new ResponseEntity<ResultDTO>(ResultUtils.onSuccess(JSONObject.toJSONString(users)),headers,HttpStatus.OK);
        else
            throw new ResultException("No Followers", ErrorCode.NOFOLLOWORFOLLOWER);
    }

}
