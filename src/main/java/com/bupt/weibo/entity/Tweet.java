package com.bupt.weibo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Entity
public class Tweet {
    private int tid;
    private int uid;
    private Integer srcId = -1;
    private String topicTitle;
    private String content;
    private Integer likes = 0;
    private Timestamp createTime;

    @Id
    @Column(name = "TID", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "UID", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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
    @Column(name = "topicTitle", nullable = true, length = 21)
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
