package com.bupt.weibo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
public class MentionPK implements Serializable {
    private int tid;
    private int uid;

    @Column(name = "TID", nullable = false)
    @Id
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Column(name = "UID", nullable = false)
    @Id
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentionPK mentionPK = (MentionPK) o;
        return tid == mentionPK.tid &&
                uid == mentionPK.uid;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tid, uid);
    }
}
