package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.repository.RelationRepository;
import com.bupt.weibo.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationRepository relationRepository;

    @Override
    public Relation addARelation(@RequestBody Relation relation){
         return relationRepository.save(relation);
    }

    @Override
    public void deleteARelation(Relation relation){
        relationRepository.delete(relation);
    }



}
