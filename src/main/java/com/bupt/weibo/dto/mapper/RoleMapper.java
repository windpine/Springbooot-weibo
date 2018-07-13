package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.RoleDTO;
import com.bupt.weibo.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role convertToEntity(RoleDTO roleDTO);

    @InheritInverseConfiguration
    RoleDTO convertToDto(Role role);

    List<Role> convertToListEntity(List<RoleDTO> roleDtoList);

    @InheritInverseConfiguration
    List<RoleDTO> conovertToListDto(List<Role> roleList);
}
