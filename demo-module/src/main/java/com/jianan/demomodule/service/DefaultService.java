package com.jianan.demomodule.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    @Override
    public void insert(){
        System.out.println("insert");
    }
}
