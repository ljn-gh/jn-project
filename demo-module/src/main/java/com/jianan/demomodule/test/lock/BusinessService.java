package com.jianan.demomodule.test.lock;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BusinessService {
    public static final ConcurrentHashMap<String,Lock> lockMap = new ConcurrentHashMap<>();
    public void approve(String id){
        Lock lock = lockMap.merge(id,new ReentrantLock(),(vo,vn) -> vo);
        boolean tryLock = lock.tryLock();
        try {
            tryLock = lock.tryLock(10, TimeUnit.SECONDS);
            if(tryLock){
                System.out.println("获取锁成功");
                System.out.println("执行业务逻辑-"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("执行业务结束-"+Thread.currentThread().getName());
            }else{
                System.out.println("获取锁失败");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(tryLock){
                lock.unlock();
                lockMap.remove(id);
            }
        }
    }
}
