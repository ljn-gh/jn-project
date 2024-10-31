package com.jianan.demomodule.test.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: jn
 * @Date: 2024/10/31
 * @description
 **/
public class RWLock {
    private static String config = "default";
    
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock rlock = rwLock.readLock();
    private static final Lock wlock = rwLock.writeLock();
    
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                boolean b = false;
                try {
                    b = rlock.tryLock(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(b){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取成功" + config);
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        rlock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + "获取失败");
                }
            }
        },"读线程1").start();


        new Thread(()->{
            while (true){
                boolean b = false;
                try {
                    b = rlock.tryLock(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(b){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取成功" + config);
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        rlock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + "获取失败");
                }
            }
        },"读线程2").start();


        new Thread(()->{
            while (true){
                boolean b = false;
                try {
                    b = wlock.tryLock(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(b){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取成功");
                        config = Thread.currentThread().getName() + "new" + System.currentTimeMillis();
                        System.out.println(Thread.currentThread().getName() + "修改数据为"+ config);
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10)+10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        wlock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + "获取失败");
                }
            }
        },"写线程2").start();

        new Thread(()->{
            while (true){
                boolean b = false;
                try {
                    b = wlock.tryLock(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(b){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获取成功");
                        config = Thread.currentThread().getName() + "new" + System.currentTimeMillis();
                        System.out.println(Thread.currentThread().getName() + "修改数据为"+ config);
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10)+10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        wlock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + "获取失败");
                }
            }
        },"写线程2").start();
    }
}
