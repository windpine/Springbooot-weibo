package com.bupt.weibo.service.impl;

import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.repository.RoleRepository;
import com.bupt.weibo.repository.UserRepository;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Role addRole(Role role) {
        role = roleRepository.save(role);
        return role;
    }

    @Override
    public List<Role> listRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public List<Role> getRolesByUserId(String userId) {
        if (!userRepository.existsById(userId)){
            throw new ResultException();
        }
        try{
            User user = userRepository.findById(userId).orElse(null);
            List<Role> roles = user.getRoles();
            return roles;
        }catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public Role updatePermissionsById(long id, List<Permission> permissions){
        if(!roleRepository.existsById(id)){
            throw new ResultException();
        }
        try {
            Role role = roleRepository.findById(id).orElse(null);
            role.setPermissions(permissions);
            role = roleRepository.save(role);
            return role;
        } catch (Exception e){
            throw new ResultException();
        }
    }

    @Override
    public void delRoleById(long id) {
        roleRepository.deleteById(id);
    }
}

