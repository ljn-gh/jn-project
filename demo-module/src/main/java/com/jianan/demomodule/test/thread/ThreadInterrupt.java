package com.jianan.demomodule.test.thread;

/**
 * @Author: jn
 * @Date: 2024/10/31
 * @description
 **/
public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // 线程执行的操作
                System.out.println("Working...");
            }
            System.out.println("Thread is interrupted!");
        });

        myThread.start();

        // 在适当的时机调用 myThread.interrupt() 来中断线程
        try {
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("中断异常");
            e.printStackTrace();
        }
    }
}
