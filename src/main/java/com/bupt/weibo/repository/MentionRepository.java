package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Mention;
import com.bupt.weibo.entity.MentionPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface MentionRepository extends JpaRepository<Mention,MentionPK> {

    List<Mention> findAllByUid(String uid);

}
