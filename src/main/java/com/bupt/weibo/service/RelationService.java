package com.bupt.weibo.service;

import com.bupt.weibo.entity.User;

import java.util.List;

/**
 * Created by niccoleynh on 2018/7/8.
 */
public interface RelationService {

    public List<User> getFollowList(Integer uid);
}
