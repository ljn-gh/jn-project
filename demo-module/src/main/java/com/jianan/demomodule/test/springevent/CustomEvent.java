package com.jianan.demomodule.test.springevent;


import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @Author: jn
 * @Date: 2024/7/22
 * @description
 **/
public class CustomEvent extends ApplicationEvent {
    private String msg;
    
    public CustomEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
    
    public CustomEvent(Object source) {
        super(source);
    }

    public CustomEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public String getMsg() {
        return msg;
    }
}
