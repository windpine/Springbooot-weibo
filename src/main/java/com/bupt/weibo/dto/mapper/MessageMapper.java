package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.MessageDTO;
import com.bupt.weibo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel ="spring")
public interface MessageMapper {

    //Message convertToEntity(MessageMentionTweetDTO MessageMentionCommentDto);

   // @InheritInverseConfiguration
    //MessageMentionTweetDTO convertToDto(Message Message);

   // List<Message> convertToListEntity(List<MessageMentionTweetDTO> MessageMentionCommentDtoList);

  //  @InheritInverseConfiguration
  //  List<MessageMentionTweetDTO> convertToListDto(List<Message> MessageList);

    @Mappings({
            @Mapping(source = "message.messageId", target = "messageID"),
            @Mapping(source = "message.srcUid",target = "srcUID"),
            @Mapping(source = "user.nickname" ,target ="nickName"),
            @Mapping(source = "message.content" ,target="content"),
            @Mapping(source = "message.type",target="messageType")
    })
    MessageDTO convertToDto(Message message, User user);
   /* @Mappings({
            @Mapping(source = "message.messageId", target = "messageId"),
            @Mapping(source = "user.nickname" ,target ="nickName"),
            @Mapping(source = "comment.content" ,target="content"),
            @Mapping(source = "comment.createTime",target="createTime")
    })
    MessageCommentDTO convertToCommentDto(Message message, Comment comment, User user);
    @Mappings({
            @Mapping(source = "message.messageId",target = "messageId"),
            @Mapping(source = "tweet.tid",target="srcId"),
            @Mapping(source = "user.nickname" ,target="nickName"),
            @Mapping(source = "tweet.content",target="content"),
    })
    MessageTweetDTO convertToDto(Message message, Tweet tweet, User user);*/

}