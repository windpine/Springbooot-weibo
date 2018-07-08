package com.bupt.weibo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Entity
public class Comment {
    private int cid;
    private int uid;
    private Integer tid;
    private Integer srcId;
    private String content;
    private Timestamp createTime;

    @Id
    @Column(name = "CID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "UID", nullable = false)
    public Integer getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "TID", nullable = true)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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
    @Column(name = "content", nullable = false, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        Comment comment = (Comment) o;
        return cid == comment.cid &&
                uid == comment.uid &&
                Objects.equals(srcId, comment.srcId) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(createTime, comment.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, uid, srcId, content, createTime);
    }
}
