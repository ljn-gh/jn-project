package com.jianan.demomodule.controller;

import com.alibaba.fastjson.JSON;
import com.jianan.demomodule.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jn
 * @Date: 2024/10/30
 * @description
 **/
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final DefaultService defaultService;
    
    @Autowired
    public UserController(DefaultService defaultService) {
        this.defaultService = defaultService;
    }
    
    @GetMapping
    public String getUser(){
        return JSON.toJSONString(defaultService.selectAll());
    }
    
    public String getUserByPage(){
        return JSON.toJSONString(defaultService.selectAll(1,20));
    }
    @PostMapping
    public String insert(){
        return defaultService.insertUser();
    }
}
