package com.jianan.demomodule.test.springevent;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: jn
 * @Date: 2024/7/22
 * @description
 **/
@Component
public class CustomEventListener {
    
    @EventListener
    @Order(1)
    public void listen1(CustomEvent event){
        System.out.println("listen1 监听到事件：" + event.getMsg());
    }

    @EventListener
    @Order(2)
    public void listen2(CustomEvent event){
        System.out.println("listen2 监听到事件：" + event.getMsg());
    }
}
