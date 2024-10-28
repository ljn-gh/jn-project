package com.jianan.demomodule.test.transaction;

import com.jianan.demomodule.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jn
 * @Date: 2024/10/25
 * @description
 **/
@Service
public class TransactionTest {
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void test(){
        userMapper.insert();
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                System.out.println("afterCommit begin");
                System.out.println(userMapper.selectUserById("jn"));
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("afterCommit end");
            }
        });
        System.out.println("insert end");
    }
}
