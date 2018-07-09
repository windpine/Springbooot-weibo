package com.bupt.weibo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
public class UserRolePK implements Serializable {
    private String uid;
    private int roleId;

    @Column(name = "uid", nullable = false)
    @Id
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(name = "role_id", nullable = false)
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolePK that = (UserRolePK) o;
        return uid == that.uid &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, roleId);
    }
}
