package com.bupt.weibo.repository;

import com.bupt.weibo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
}
