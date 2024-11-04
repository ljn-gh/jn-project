package com.jianan.demomodule.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jianan.demomodule.mapper.UserMapper;
import com.jianan.demomodule.model.User;
import com.jianan.demomodule.service.IDefaultService;
import com.jianan.demomodule.service.UserService;
import com.jianan.demomodule.test.springevent.CustomEvent;
import com.jianan.demomodule.test.transaction.TransactionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private TransactionTest transactionTest;
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

    @GetMapping("transaction")
    public void transaction() {
        transactionTest.test();
    }
    public static void main(String[] args) {
        double a = 0.0000221;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(0);
        System.out.println(nf.format(a));
        System.out.println(Double.MAX_VALUE);
    }
    
    
    @Cacheable(value = "defaultCache",cacheManager = "ehCacheCacheManager",key = "#user.id" )
    @GetMapping("cache")
    public String cache(User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", user.getId());
        System.out.println("查询数据库-------------------------");
        return JSON.toJSONString(userMapper.selectOne(wrapper));
    }
    
    
    @PostMapping("update")
    @CacheEvict(value = "defaultCache",cacheManager = "ehCacheCacheManager",key = "#user.id")
    public void update(@RequestBody User user){
        UpdateWrapper update = new UpdateWrapper();
        update.eq("id", user.getId());
        userMapper.update(user,update);
        System.out.println("更新数据库------------------------");
    }
}