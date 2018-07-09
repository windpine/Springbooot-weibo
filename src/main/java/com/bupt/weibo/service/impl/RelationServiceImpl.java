package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.repository.RelationRepository;
import com.bupt.weibo.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationRepository relationRepository;

    @Override
    public Relation addARelation(@RequestBody Relation relation){
         return relationRepository.save(relation);
    }

    @Override
    public void deleteARelation(int followId, int followerId){
        Relation relation=new Relation();
        relation.setFollowId(followId);
        relation.setFollowerId(followerId);
        relationRepository.delete(relation);
    }

    @Override
    public List<User> getAllFollows(int uid){
        return relationRepository.findByFollowId(uid);
    }

    @Override
    public List<User> getAllFollowers(int uid){
        return relationRepository.findByFollowerId(uid);
    }

}
