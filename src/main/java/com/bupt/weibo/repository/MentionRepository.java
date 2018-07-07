package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Mention;
import com.bupt.weibo.entity.MentionPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface MentionRepository extends JpaRepository<Mention,MentionPK> {
}
