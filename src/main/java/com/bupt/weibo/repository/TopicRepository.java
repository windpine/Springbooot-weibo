package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface TopicRepository extends JpaRepository<Topic,Integer> {

}
