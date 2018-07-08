package com.bupt.weibo.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by niccoleynh on 2018/7/8.
 */
@Data
public class UserDTO {
    private int uid;
    private String username;
    private String nickname;
    private String password;
    //    private Integer tweets;
//    private Integer follows;
//    private Integer followers;
    private String sex;
//    private Timestamp creatTime;
//    private String role;
//    private String email;
}
