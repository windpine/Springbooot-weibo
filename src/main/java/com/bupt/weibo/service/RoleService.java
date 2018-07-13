package com.bupt.weibo.service;

import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
public interface RoleService {
    Role addRole(Role role);

    List<Role> listRoles();

    List<Role> getRolesByUserId(String userId);

    Role updatePermissionsById(long id,List<Permission> permissions);

    void delRoleById(long id);
}
