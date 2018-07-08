package com.bupt.weibo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Tweet {
    private int tid;
    private int uid;
    private Integer srcId;
    private String topicTitle;
    private String content;
    private Integer likes;
    private Timestamp createTime;

    @Id
    @Column(name = "TID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "UID")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "srcID")
    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    @Basic
    @Column(name = "topicTitle")
    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "likes")
    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return tid == tweet.tid &&
                uid == tweet.uid &&
                Objects.equals(srcId, tweet.srcId) &&
                Objects.equals(topicTitle, tweet.topicTitle) &&
                Objects.equals(content, tweet.content) &&
                Objects.equals(likes, tweet.likes) &&
                Objects.equals(createTime, tweet.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tid, uid, srcId, topicTitle, content, likes, createTime);
    }
}
