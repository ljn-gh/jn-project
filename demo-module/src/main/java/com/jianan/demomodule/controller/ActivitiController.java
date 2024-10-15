package com.jianan.demomodule.controller;

import com.jianan.demomodule.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jn
 * @Date: 2024/10/12
 * @description
 **/
@RestController("acti")
public class ActivitiController {
    
    @Autowired
    private ActivitiService activitiService;
    
    @GetMapping("start")
    public void start() {
        activitiService.start();
    }
}
