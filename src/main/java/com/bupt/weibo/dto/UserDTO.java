package com.bupt.weibo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by niccoleynh on 2018/7/8.
 */
@Data
@EqualsAndHashCode
public class UserDTO {
    private String uid;
    private String username;
    private String password;
    private String email;
    private String nickname;
    //    private Integer tweets;
//    private Integer follows;
//    private Integer followers;
//    private Timestamp creatTime;
//    private String role;
    private List<RoleDTO> roles;
    private List<PermissionDto> permissions;



}