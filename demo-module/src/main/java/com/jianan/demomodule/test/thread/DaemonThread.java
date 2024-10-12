package com.jianan.demomodule.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * DaemonThread
 * @description
 * @author lijianan
 * @date 2024/4/18 10:14
 * @version TODO
 */
public class DaemonThread {
    public static void main(String[] args) {
        new Thread(()->{
            Thread t = new Thread(()->{
                while(true){
                    System.out.println("子线程守护线程");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }
            });
            t.setDaemon(true);
            t.start();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {

            }
            while(true){
                System.out.println("子线程");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
    }
}