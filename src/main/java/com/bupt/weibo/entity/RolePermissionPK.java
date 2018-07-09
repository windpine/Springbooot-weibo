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
public class RolePermissionPK implements Serializable {
    private int roleId;
    private int permissionId;

    @Column(name = "role_id", nullable = false)
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "permission_id", nullable = false)
    @Id
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
        RolePermissionPK that = (RolePermissionPK) o;
        return roleId == that.roleId &&
                permissionId == that.permissionId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, permissionId);
    }
}
