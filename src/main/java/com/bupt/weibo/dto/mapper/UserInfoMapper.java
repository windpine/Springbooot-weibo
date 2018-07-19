package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.UserInfo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserInfo convertToEntity(UserInfoDTO userInfoDTO);

    @InheritInverseConfiguration
    UserInfoDTO convertToDto(UserInfo userInfo);

    UserInfo convertToEntity(UserDTO userDTO);
}
