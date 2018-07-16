package com.bupt.weibo.service;

import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.Relation;

import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;

import java.util.List;


public interface RelationService {

    void addARelation(String followId, String followerId);

    void deleteARelation(String followId, String followerId);

    List<UserInfoDTO> getAllFollows(String uid);

    List<UserInfoDTO> getAllFollowers(String uid);

    List<Relation> getAllRelations();

    List<Relation> findById(String followId,String followerId);

}
