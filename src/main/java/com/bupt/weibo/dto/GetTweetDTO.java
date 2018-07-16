package com.bupt.weibo.dto;

import java.sql.Timestamp;

public class GetTweetDTO {

    private int tid;
    private String uid;
    private Integer srcId=-1;
    private GetTweetDTO srcTweet;
    private String topicTitle;
    private String content;
    private Integer likes;
    private Timestamp createTime;
    private Integer forwards=0;
    private Integer comments=0;
    private String nickName;
    private String avatarUrl ="";
}
