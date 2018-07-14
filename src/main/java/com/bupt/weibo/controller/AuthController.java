package com.bupt.weibo.controller;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.dto.RoleDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.enums.ErrorCode;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by shusesshou on 2017/9/22.
 */
@RestController
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String SUBPATH_LOGIN = "/login";
    public static final String SUBPATH_USERINFO = "/userInfo";




    @PostMapping(value = SUBPATH_LOGIN)
    public ResponseEntity<ResultDTO> login(@RequestBody UserDTO userDto,
                                         UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,SUBPATH_LOGIN);
        logger.info("================userInfo================username: " + userDto.getUsername() + ",pw: " + userDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(),userDto.getPassword());
        try{
            subject.login(token);
        } catch (AuthenticationException e){
            logger.error("======登录失败======");
            throw new ResultException(ErrorCode.USERNAMEORPASSWORD.getDesc(),ErrorCode.USERNAMEORPASSWORD);
        }
        String loginUserDto = (String) SecurityUtils.getSubject().getSession().getAttribute("uid");
        Map uidResult = new HashMap<String,String>();
        uidResult.put("uid",loginUserDto);
        return new ResponseEntity<>(ResultUtils.onSuccess(uidResult),headers, HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_USERINFO)
    public ResponseEntity<UserDTO> getUserInfo(UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,SUBPATH_USERINFO);
        UserDTO userDto = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("user");

        return new ResponseEntity<UserDTO>(userDto,headers,HttpStatus.OK);
    }

    @GetMapping(value = "notAuthc")
    public void notAuthc(UriComponentsBuilder uriComponentsBuilder){
        throw new ResultException("Not Authc", ErrorCode.NOTAUTHC);
    }

    @GetMapping(value = "notAuthz")
    public void notAuthz(UriComponentsBuilder uriComponentsBuilder){
        User loginUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        throw new ResultException(
                "UserName: " + loginUser.getUsername()
                        + "\nPermissions: " + loginUser.getPermissions().toString()
                        + "\nRoles: " + loginUser.getRoles().toString()
                , ErrorCode.NOTAUTHC);
    }

    private UserDTO convertToDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setUid((user.getUid()));
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private RoleDTO convertToDto(Role role){
        RoleDTO roleDto = new RoleDTO();
        roleDto.setName(role.getName());
        return roleDto;
    }
}