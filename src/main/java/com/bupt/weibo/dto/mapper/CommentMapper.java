package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.CommentDTO;
import com.bupt.weibo.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface CommentMapper {

    Comment convertToEntity(CommentDTO CommentDto);

    @InheritInverseConfiguration
    CommentDTO convertToDto(Comment Comment);

    List<Comment> convertToListEntity(List<CommentDTO> CommentDtoList);

    @InheritInverseConfiguration
    List<CommentDTO> convertToListDto(List<Comment> CommentList);

}
