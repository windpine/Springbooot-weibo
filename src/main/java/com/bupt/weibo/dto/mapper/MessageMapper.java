package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.MessageCommentDTO;
import com.bupt.weibo.dto.MessageMentionTweetDTO;
import com.bupt.weibo.entity.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel ="spring")
public interface MessageMapper {

    Message convertToEntity(MessageMentionTweetDTO MessageMentionCommentDto);

    @InheritInverseConfiguration
    MessageMentionTweetDTO convertToDto(Message Message);

    List<Message> convertToListEntity(List<MessageMentionTweetDTO> MessageMentionCommentDtoList);

    @InheritInverseConfiguration
    List<MessageMentionTweetDTO> convertToListDto(List<Message> MessageList);

    @Mappings({
            @Mapping(source = "message.messageId", target = "messageId"),
            @Mapping(source = "message.srcId",target = "srcId"),
            @Mapping(source = "user.nickname" ,target ="nickName"),
            @Mapping(source = "tweet.content" ,target="content"),
            @Mapping(source = "mention.createTime",target="createTime")
    })
    MessageMentionTweetDTO convertToMentionDto(Message message, Mention mention, Tweet tweet,User user);
    @Mappings({
            @Mapping(source = "message.messageId", target = "messageId"),
            @Mapping(source = "user.nickname" ,target ="nickName"),
            @Mapping(source = "comment.content" ,target="content"),
            @Mapping(source = "comment.createTime",target="createTime")
    })
    MessageCommentDTO convertToCommentDto(Message message, Comment comment, User user);

}