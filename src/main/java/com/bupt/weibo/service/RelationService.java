package com.bupt.weibo.service;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.User;

import java.util.List;

public interface RelationService {

    Relation addARelation(Relation relation);

    void deleteARelation(int followId, int followerId);

    List<User> getAllFollows(int uid);

    List<User> getAllFollowers(int uid);

}
