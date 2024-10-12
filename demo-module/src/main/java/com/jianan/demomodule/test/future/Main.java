package com.jianan.demomodule.test.future;

import org.junit.Test;

import java.util.concurrent.*;

public class Main {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {
        
        CompletableFuture completableFuture = new CompletableFuture();

        System.out.println("主线程");
        CompletableFuture<Void> f1 = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName()+"第一个线程开始执行！");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                
            }
            System.out.println(Thread.currentThread().getName()+"第一个线程执行结束！");
            return 1;
        },executorService).thenRun(()->{
            System.out.println(Thread.currentThread().getName()+"第二个线程开始执行！");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                
            }
            System.out.println(Thread.currentThread().getName()+"第二个线程执行结束！");
        }).thenRun(()->{
            System.out.println(Thread.currentThread().getName()+"第三个线程开始执行！");
        });
        System.out.println("主线程");
        //f1.thenAccept();

        
    }
    @Test
    public void test() throws ExecutionException, InterruptedException {
        Future<String> submit = executorService.submit(() -> System.out.println("线程"), "12312312312");
        System.out.println(submit.get());
        System.out.println("111111111");
    }
}
