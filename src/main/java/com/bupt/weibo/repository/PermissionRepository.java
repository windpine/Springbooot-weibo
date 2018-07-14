package com.bupt.weibo.repository;

import com.bupt.weibo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
