package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.UserInfoDto;
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

    UserInfo convertToEntity(UserInfoDto userInfoDto);

    @InheritInverseConfiguration
    UserInfoDto convertToDto(UserInfo userInfo);
}
