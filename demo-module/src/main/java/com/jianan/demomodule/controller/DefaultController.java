package com.jianan.demomodule.controller;

import com.jianan.demomodule.service.IDefaultService;
import com.jianan.demomodule.test.springevent.CustomEvent;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * DefaultController
 * @description
 * @author lijianan
 * @date 2024/3/25 14:06
 * @version TODO
 */
@RequestMapping
@RestController
public class DefaultController {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Autowired
    private IDefaultService service;
    @PostMapping("index")
    public void index(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
    }

    @GetMapping("get")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<script>\n" +
                "    document.addEventListener('keydown', function (e) {\n" +
                "        if (e.ctrlKey && e.key === 's') {\n" +
                "            e.preventDefault();\n" +
                "        }\n" +
                "    })\n" +
                "</script>");
        String requestURI = request.getRequestURI();
        request.getQueryString();
    }
    
    @GetMapping("publishEvent")
    public void publishEvent(){
        publisher.publishEvent(new CustomEvent(this,"测试事件发布"));
        service.insert();
    }

    public static void main(String[] args) {
        double a = 0.0000221;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(0);
        System.out.println(nf.format(a));
        System.out.println(Double.MAX_VALUE);
    }
}