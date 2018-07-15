package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.TweetDTO;
import com.bupt.weibo.entity.Tweet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface TweetMapper {

    Tweet convertToEntity(TweetDTO TweetDto);

    @InheritInverseConfiguration
    TweetDTO convertToDto(Tweet Tweet);

    List<Tweet> convertToListEntity(List<TweetDTO> TweetDtoList);

    @InheritInverseConfiguration
    List<TweetDTO> convertToListDto(List<Tweet> TweetList);
    
}
