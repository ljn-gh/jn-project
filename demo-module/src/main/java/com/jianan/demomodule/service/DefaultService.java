package com.jianan.demomodule.service;

import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianan.demomodule.mapper.UserMapper;
import com.jianan.demomodule.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/6/11
 * @description
 **/
@Service
@Transactional
public class DefaultService implements IDefaultService{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    private final UserMapper userMapper;

    @Autowired
    public DefaultService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public void insert(){
        System.out.println("insert");
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public String insertUser() {
        User user = new User();
        user.setId("mptest");
        user.setName("mp");
        userMapper.insert(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "mptest");
        return JSON.toJSONString(userMapper.selectOne(queryWrapper));
    }

    @Override
    public List<User> selectAll(int pageNum, int pageSize) {
        IPage<User> page = new Page<>(1, 10);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        return userIPage.getRecords();
    }
}
