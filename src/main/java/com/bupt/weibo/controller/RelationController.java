package com.bupt.weibo.controller;


import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.service.RelationService;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/{followId}/follow/{followerID}")
    @DeleteMapping("/")
    public ResultDTO deleteARelation(Relation relation) throws Exception{
        relationService.deleteARelation(relation);
        return resultUtils.onSuccess();
    }






}
