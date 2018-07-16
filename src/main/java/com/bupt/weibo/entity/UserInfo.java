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
 * Created by niccoleynh on 2018/7/15.
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
    @Column(name = "tweets", nullable = true,length = 11)
    private Integer tweets=0;
    @Basic
    @Column(name = "follows", nullable = true,length = 11)
    private Integer follows=0;
    @Basic
    @Column(name = "followers", nullable = true,length = 11)
    private Integer followers=0;
    @Basic
    @Column(name = "sex", nullable = true,length = 2)
    private String sex;
    @Basic
    @Column(name = "avatar_url", nullable = true,length = 256)
    private String avatarUrl ="";
//    @Basic
//    @Column(name = "creat_time", nullable = false)
//    private Timestamp creatTime;


//    public String getUid(){
//        return uid;
//    }
//
//    public String getSex(){
//        return sex;
//    }
//
//    public String getAvatarUrl(){
//        return avatarUrl;
//    }
//

}