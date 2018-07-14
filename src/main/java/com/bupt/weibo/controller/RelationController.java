package com.bupt.weibo.controller;


import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.RelationService;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class RelationController {

    @Autowired
    RelationService relationService;

    @Autowired
    ResultUtils resultUtils;


    @RequestMapping("/follow")
    @PostMapping("/")
    public ResultDTO addARelation(Relation relation) throws Exception{
        if(relationService.addARelation(relation)!=null)
            return resultUtils.onSuccess();
        else
            return resultUtils.onError();
    }

    @RequestMapping(value="/{followId}/follow/{followerId}")
    @DeleteMapping("/")
    public ResultDTO deleteARelation(@PathVariable int followId,@PathVariable int followerId) throws Exception{
        relationService.deleteARelation(followId,followerId);
        return resultUtils.onSuccess();
    }

    @RequestMapping(value = "/{uid}/follows")
    @GetMapping("/")
    public ResultDTO getAllFollows(@PathVariable int uid) throws Exception{
        List<User> users=relationService.getAllFollows(uid);
        if(users!=null)
            return resultUtils.onSuccess(JSONObject.toJSONString(users));
        else
            return resultUtils.onError();
    }

    @RequestMapping(value = "/{uid}/followers")
    @GetMapping("/")
    public ResultDTO getAllFollowers(@PathVariable int uid) throws Exception{
        List<User> users=relationService.getAllFollowers(uid);
        if(users!=null)
            return resultUtils.onSuccess(JSONObject.toJSONString(users));
        else
            return resultUtils.onError();
    }






}
