package com.bupt.weibo.dto;

import com.bupt.weibo.entity.UserInfo;
import lombok.Data;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Data
public class UserInfoDTO {


    private String uid;

    private String nickname;//user

    private String username;//user

    private Integer tweets=0;

    private Integer follows=0;

    private Integer followers=0;

    private String avatarUrl ="";

    private String sex;//user

    private String password;//user

    private String email;//user

    public UserInfoDTO(){};
    public UserInfoDTO(String uid,String nickname,String username,
                Integer tweets,Integer follows,Integer followers,
                String avatarUrl,String sex,String password,String email){
        this.uid=uid;
        this.nickname=nickname;
        this.username=username;
        this.tweets=tweets;
        this.follows=follows;
        this.followers=followers;
        this.avatarUrl=avatarUrl;
        this.sex=sex;
        this.password=password;
        this.email=email;
    };

}
