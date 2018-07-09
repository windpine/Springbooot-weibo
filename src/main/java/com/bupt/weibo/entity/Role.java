package com.bupt.weibo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Entity
public class Role {
    private int roleId;
    private String role;
    private String description;

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 32)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 128)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return roleId == role1.roleId &&
                Objects.equals(role, role1.role) &&
                Objects.equals(description, role1.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, role, description);
    }
}
