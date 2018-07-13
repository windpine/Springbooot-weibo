package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.repository.PermissionRepository;
import com.bupt.weibo.repository.RoleRepository;
import com.bupt.weibo.repository.UserRepository;
import com.bupt.weibo.service.PermissionService;
import com.bupt.weibo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public Permission addPermission(Permission permission) {
        permissionRepository.save(permission);
        return null;
    }

    @Override
    public List<Permission> listPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions;
    }

    @Override
    public List<Permission> getPermissionsByUserId(String userId) {
        if(!userRepository.existsById(userId)){
            throw new ResultException();
        }
        try {
            User user = userRepository.findById(userId).orElse(null);
            List<Permission> permissions = user.getPermissions();
            return permissions;
        }catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public void delPermissionById(long id) {
        permissionRepository.deleteById(id);
    }

}
