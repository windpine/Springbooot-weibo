package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.entity.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface MessageMapper {

    Message convertToEntity(MessageDTO MessageDto);

    @InheritInverseConfiguration
    MessageDTO convertToDto(Message Message);

    List<Message> convertToListEntity(List<MessageDTO> MessageDtoList);

    @InheritInverseConfiguration
    List<MessageDTO> convertToListDto(List<Message> MessageList);

}