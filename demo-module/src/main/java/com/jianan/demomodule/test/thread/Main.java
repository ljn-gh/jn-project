package com.jianan.demomodule.test.thread;

public class Main {
    public static final String LOCK1 = "LOCK1";
    public static final String LOCK2 = "LOCK2";
    
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (LOCK1){
                System.out.println(Thread.currentThread().getName()+"获取到锁"+LOCK1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (LOCK2){
                    System.out.println(Thread.currentThread().getName()+"获取到锁"+LOCK2);
                }
            }
            
        }).start();

        new Thread(()->{
            synchronized (LOCK2){
                System.out.println(Thread.currentThread().getName()+"获取到锁"+LOCK2);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (LOCK1){
                    System.out.println(Thread.currentThread().getName()+"获取到锁"+LOCK1);
                }
            }

        }).start();
    }
}
