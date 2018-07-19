package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.entity.*;
import com.bupt.weibo.entity.enums.MessageType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel ="spring")
public interface MessageMapper {
    //userName映射到标题所用的name
    @Mappings({
            @Mapping(source = "message.messageId", target = "messageID"),
            @Mapping(source = "message.srcUid",target = "srcUID"),
            @Mapping(source = "user.username" ,target ="nickName"),
            @Mapping(source = "message.content" ,target="content"),
            @Mapping(source = "message.type",target="messageType")
    })
    MessageDTO convertToDto(Message message, User user);
    @Mappings({
            @Mapping(source = "relation.followerId",target = "srcUID"),
            @Mapping(source = "user.username" ,target = "nickName"),
            @Mapping(source = "tweet.content", target = "content"),
    })
    MessageDTO convertToDto(Relation relation,Tweet tweet,User user);
}