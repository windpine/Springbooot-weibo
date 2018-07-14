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
@IdClass(MentionPK.class)
public class Mention {
    private int tid;
    private String uid;
    private Integer cid;
    private Timestamp createTime;

    @Id
    @Column(name = "TID", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Id
    @Column(name = "UID", nullable = false)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "CID", nullable = true)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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
        Mention mention = (Mention) o;
        return tid == mention.tid &&
                Objects.equals(uid , mention.uid) &&
                Objects.equals(cid, mention.cid) &&
                Objects.equals(createTime, mention.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tid, uid, cid, createTime);
    }
}
