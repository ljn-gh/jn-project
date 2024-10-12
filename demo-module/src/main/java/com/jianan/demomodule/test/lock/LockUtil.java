package com.jianan.demomodule.test.lock;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:
 * @Date: 2024/3/5
 * @description
 **/

@Component
public class LockUtil {
    public static final ConcurrentHashMap<String, Lock> lockMap = new ConcurrentHashMap<>();
    
    public void lock(String key)
    {
        Lock lock = lockMap.get(key);
        if(lock == null)
        {
            lock = new ReentrantLock();
            Lock oldLock = lockMap.putIfAbsent(key,lock);
            if(oldLock != null)
            {
                lock = oldLock;
            }
        }
        lock.lock();
    }
}
