package com.bupt.weibo.dto;

import com.bupt.weibo.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Data
@EqualsAndHashCode
public class CommentGetDTO {
    private int cid;
    private String uid;
    private Integer tid;
    private Integer srcId=-1;
    private String content;
    private Timestamp createTime;

    private String nickname;

    private String avatarUrl ="";

    public CommentGetDTO(Comment c,String nickname,String avatarUrl){
        this.cid=c.getCid();
        this.uid=c.getUid();
        this.tid=c.getTid();
        this.srcId=c.getSrcId();
        this.content=c.getContent();
        this.createTime=c.getCreateTime();

        this.nickname=nickname;

        this.avatarUrl=avatarUrl;
    }
}

