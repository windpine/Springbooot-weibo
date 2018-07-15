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
    private String followId;
    private String followerId;

    @Column(name = "followID", nullable = false)
    @Id
    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    @Column(name = "followerID", nullable = false)
    @Id
    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationPK that = (RelationPK) o;
        return Objects.equals(followId , that.followId) &&
               Objects.equals( followerId , that.followerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(followId, followerId);
    }
}
