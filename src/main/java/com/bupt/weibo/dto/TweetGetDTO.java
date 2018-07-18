package com.bupt.weibo.dto;

import com.bupt.weibo.entity.Tweet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode
public class TweetGetDTO {

    private int tid;
    private String uid;
    private Integer srcId;
    private String topicTitle;
    private String content;
    private Integer likes;
    private Timestamp createTime;
    private Integer forwards=0;
    private Integer comments=0;
    private String imageUrl="";

    private String nickname;

    private String avatarUrl ="";

    public TweetGetDTO(Tweet t,String nickname,String avatarUrl){
        this.tid=t.getTid();
        this.uid=t.getUid();
        this.srcId=t.getSrcId();
        this.topicTitle=t.getTopicTitle();
        this.content=t.getContent();
        this.likes=t.getLikes();
        this.createTime=t.getCreateTime();
        this.forwards=getForwards();
        this.comments=t.getComments();
        this.imageUrl=t.getImageUrl();

        this.nickname=nickname;

        this.avatarUrl=avatarUrl;
    }
}
