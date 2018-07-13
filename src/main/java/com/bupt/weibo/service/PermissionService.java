package com.bupt.weibo.service;

import com.bupt.weibo.entity.Permission;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
public interface PermissionService {

    Permission addPermission(Permission permission);
    List<Permission> listPermissions();
    List<Permission> getPermissionsByUserId(String userId);

    void delPermissionById(long id);
}
