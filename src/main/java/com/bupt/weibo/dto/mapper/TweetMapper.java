package com.bupt.weibo.dto.mapper;

import com.bupt.weibo.dto.TweetDTO;
import com.bupt.weibo.entity.Tweet;
import org.mapstruct.Mapper;

/**
 * @anthor tanshangou
 * @time 2018/7/15
 * @description
 */
@Mapper(componentModel = "spring")
public interface TweetMapper {

    Tweet convertToEntity(TweetDTO tweetDTO);
}
