package com.bupt.weibo.service;

import com.bupt.weibo.entity.Relation;

public interface RelationService {

    Relation addARelation(Relation relation);

    void deleteARelation(Relation relation);
}
