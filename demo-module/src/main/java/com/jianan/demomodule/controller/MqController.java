package com.jianan.demomodule.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jn
 * @Date: 2024/12/6
 * @description
 **/
@RestController
@RequestMapping("/mq")
public class MqController {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    
    /**
     * 发送消息
     */
    @PostMapping("send")
    public void sendMessage(String message){
        rocketMQTemplate.syncSend(topic, message);
    }
}
