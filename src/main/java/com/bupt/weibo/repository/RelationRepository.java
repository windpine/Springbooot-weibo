package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Repository
@Transactional
public interface RelationRepository extends JpaRepository<Relation,RelationPK> {
    
}
