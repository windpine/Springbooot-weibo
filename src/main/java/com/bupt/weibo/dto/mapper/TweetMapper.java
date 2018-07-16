package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.PostTweetDTO;
import com.bupt.weibo.entity.Tweet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface TweetMapper {

    Tweet convertToEntity(PostTweetDTO postTweetDto);

    @InheritInverseConfiguration
    PostTweetDTO convertToDto(Tweet Tweet);

    List<Tweet> convertToListEntity(List<PostTweetDTO> postTweetDtoList);

    @InheritInverseConfiguration
    List<PostTweetDTO> convertToListDto(List<Tweet> TweetList);
    
}
