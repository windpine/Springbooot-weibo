package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.MentionDTO;
import com.bupt.weibo.entity.Mention;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @anthor tanshangou
 * @time 2018/7/16
 * @description
 */

@Mapper(componentModel = "spring")
public interface MentionMapper {

    Mention convertToEntity(MentionDTO mentionDTO);

    @InheritInverseConfiguration
    MentionDTO convertToDto(Mention mention);
}
