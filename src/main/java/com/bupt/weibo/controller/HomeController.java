package com.bupt.weibo.controller;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        // 创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            currentUser.login(token);
            // 判断当前用户是否登录
            if (currentUser.isAuthenticated() == true) {
                return "/index.html";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
        return "/loginPage.html";
    }

    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password) throws AuthenticationException {

        UserDTO userDTO = new UserDTO();
        User user = userService.registerUser(userDTO);
        if(user!=null){
            return "/login";
        }
        return "/register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        log.info("login() 方法被调用");
        return "loginPage.html";
    }

    @RequestMapping(value = "/register")
    public String register() {
        log.info("register() 方法被调用");
        return "registerPage.html";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        log.info("hello() 方法被调用");
        return "helloPage.html";
    }
}
