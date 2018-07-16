package com.bupt.weibo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Entity
@Table(name = "user_info")
@Data
@EqualsAndHashCode
public class UserInfo {

    @Id
    @Column(name = "UID", nullable = false,length = 32)
    private String uid;
    @Basic
    @Column(name = "nickname", nullable = true, length = 32)
    private String nickname;
    @Basic
    @Column(name = "tweets", nullable = true,length = 11)
    private Integer tweets=0;
    @Basic
    @Column(name = "follows", nullable = true,length = 11)
    private Integer follows=0;
    @Basic
    @Column(name = "followers", nullable = true,length = 11)
    private Integer followers=0;
    @Basic
    @Column(name = "avatar_url", nullable = true,length = 256)
    private String imageUrl="";
    @Basic
    @Column(name = "creat_time", nullable = false)
    private Timestamp creatTime;
}
