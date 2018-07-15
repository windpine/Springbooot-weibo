package com.bupt.weibo.service;

import com.bupt.weibo.entity.Relation;

import com.bupt.weibo.entity.User;

import java.util.List;


public interface RelationService {

    Relation addARelation(Relation relation);

    void deleteARelation(String followId, String followerId);

    List<User> getAllFollows(String uid);

    List<User> getAllFollowers(String uid);

}
