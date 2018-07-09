package com.bupt.weibo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Entity
@Table(name = "user_role", schema = "weibo", catalog = "")
@IdClass(UserRolePK.class)
public class UserRole {
    private String uid;
    private int roleId;

    @Id
    @Column(name = "uid", nullable = false)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Id
    @Column(name = "role_id", nullable = false)
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
        UserRole userRole = (UserRole) o;
        return uid == userRole.uid &&
                roleId == userRole.roleId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, roleId);
    }
}
