package com.bupt.weibo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Entity
public class Tweet {
    private int tid;
    private String uid;
    private Integer srcId=-1;
    private String topicTitle;
    private String content;
    private Integer likes=0;
    private Timestamp createTime;
    private Integer forwards=0;
    private Integer comments=0;
    private String imageUrl="";


    @Id
    @Column(name = "TID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "UID", nullable = false)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "srcID", nullable = true)
    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    @Basic
    @Column(name = "topic_title", nullable = true, length = 21)
    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "likes", nullable = true)
    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "forwards", nullable = true,length = 11)
    public Integer getForwards() {
        return forwards;
    }

    public void setForwards(Integer forwards) {
        this.forwards = forwards;
    }
    @Basic
    @Column(name = "comments", nullable = true,length = 11)
    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "image_url", nullable = true,length = 256)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return tid == tweet.tid &&
                Objects.equals(uid ,tweet.uid) &&
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
