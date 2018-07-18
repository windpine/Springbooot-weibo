package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.TweetPostDTO;
import com.bupt.weibo.entity.Tweet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface TweetMapper {

    Tweet convertToEntity(TweetPostDTO tweetPostDto);

    @InheritInverseConfiguration
    TweetPostDTO convertToDto(Tweet Tweet);

    List<Tweet> convertToListEntity(List<TweetPostDTO> tweetPostDtoList);

    @InheritInverseConfiguration
    List<TweetPostDTO> convertToListDto(List<Tweet> TweetList);
    
}
