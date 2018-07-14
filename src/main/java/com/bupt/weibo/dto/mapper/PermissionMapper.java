package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.PermissionDto;
import com.bupt.weibo.entity.Permission;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission convertToEntity(PermissionDto permissionDto);

    @InheritInverseConfiguration
    PermissionDto convertToDto(Permission permission);

    List<Permission> convertToListEntity(List<PermissionDto> permissionDtoList);

    @InheritInverseConfiguration
    List<PermissionDto> convertToListDto(List<Permission> permissionList);
}
