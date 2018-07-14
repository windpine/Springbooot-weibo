package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.utils.UUIDUtils;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */

@Mapper(componentModel = "spring")
public interface UserMapper {


    //注册时默认添加角色为User
    //权限和身份的设置转移到业务层
     User convertToEntity(UserDTO userDTO);


    //TODO 用于后台管理时手动添加，需要额外参数


    //登陆时信息校验
    @InheritInverseConfiguration
    UserDTO convertToDto(User user);

    List<User> convertToListEntity(List<UserDTO> roleDtoList);

    @InheritInverseConfiguration
    List<UserDTO> conovertToListDto(List<User> roleList);
}
