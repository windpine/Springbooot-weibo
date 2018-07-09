package com.bupt.weibo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Entity
@Table(name = "role_permission", schema = "weibo", catalog = "")
@IdClass(RolePermissionPK.class)
public class RolePermission {
    private int roleId;
    private int permissionId;

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "permission_id", nullable = false)
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermission that = (RolePermission) o;
        return roleId == that.roleId &&
                permissionId == that.permissionId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, permissionId);
    }
}
