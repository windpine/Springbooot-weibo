package com.bupt.weibo.dto;

import lombok.Data;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@Data
public class UserInfoDTO {


    private String uid;

    private String nickname;

    private Integer tweets=0;

    private Integer follows=0;

    private Integer followers=0;

    private String avatarUrl ="";

}
