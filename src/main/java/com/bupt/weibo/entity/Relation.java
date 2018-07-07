package com.bupt.weibo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Entity
@IdClass(RelationPK.class)
public class Relation {
    private int followId;
    private int followerId;

    @Id
    @Column(name = "followID", nullable = false)
    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    @Id
    @Column(name = "followerID", nullable = false)
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
        Relation relation = (Relation) o;
        return followId == relation.followId &&
                followerId == relation.followerId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(followId, followerId);
    }
}
