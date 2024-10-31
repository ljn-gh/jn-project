package com.jianan.demomodule.service;

import com.jianan.demomodule.model.User;

import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/7/22
 * @description
 **/
public interface IDefaultService{
    void insert();
    
    List<User> selectAll();
    
    List<User> selectAll(int pageNum, int pageSize);
    
    String insertUser();

}
