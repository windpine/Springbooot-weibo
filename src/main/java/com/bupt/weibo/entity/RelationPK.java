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
public class RelationPK implements Serializable {
    private int followId;
    private int followerId;

    @Column(name = "followID", nullable = false)
    @Id
    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    @Column(name = "followerID", nullable = false)
    @Id
    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationPK that = (RelationPK) o;
        return followId == that.followId &&
                followerId == that.followerId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(followId, followerId);
    }
}
