package com.bupt.weibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/sayhi")
    public String sayHi(){
        return "hi";
    }
}
