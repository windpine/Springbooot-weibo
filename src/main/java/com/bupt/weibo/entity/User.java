package com.bupt.weibo.entity;

import com.bupt.weibo.entity.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "UID", nullable = false)
    private String uid;
    @Basic
    @Column(name = "username", nullable = false, length = 32)
    private String username;
    @Basic
    @Column(name = "nickname", nullable = true, length = 32)
    private String nickname;
    @Basic
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Basic
    @Column(name = "creat_time", nullable = false)
    private Timestamp creatTime;
    @Basic
    @Column(name = "email", nullable = false, length = 32)
    private String email;

    /**
     * 添加
     */

    @Column(name = "status")
    @NotNull
    private UserStatus status = UserStatus.CREATE;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "UID",referencedColumnName = "UID")},
            inverseJoinColumns = {@JoinColumn(name = "roleId",referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_permission",
            joinColumns = {@JoinColumn(name = "userId",referencedColumnName = "UID")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId",referencedColumnName = "id")})
    private List<Permission> permissions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) &&
                Objects.equals(username, user.username) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(creatTime, user.creatTime) &&
                Objects.equals(email, user.email) &&
                status == user.status &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, username, nickname, password, creatTime, email,status, roles, permissions);
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
