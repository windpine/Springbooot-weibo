package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Transactional
    List<Message> findAllByUid(Integer UID);
}
