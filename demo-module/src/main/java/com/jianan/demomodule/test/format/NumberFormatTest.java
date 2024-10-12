package com.jianan.demomodule.test.format;

import org.junit.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: jn
 * @Date: 2024/4/18
 * @description
 **/
public class NumberFormatTest {

    private static final int NUM_THREADS = 10;
    private static final int NUM_ITERATIONS = 1000;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    String formattedNumber = NUMBER_FORMAT.format(counter.incrementAndGet());
                    System.out.println(Thread.currentThread().getName() + ": " + formattedNumber);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        double[] arr = {11.1111111d, 22.2222222d, 33.3333333d, 44.4444444d};
        for (double v : arr) {
            new Thread(()->{
                for(int i = 0; i < 20; i++){
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + "-------" + v);
                    
                    System.out.println(Thread.currentThread().getName() + "-------" + format.format(v));
                }
            }).start();
        }
        
        
        //date.parallelStream().forEach(d -> System.out.println(s.format(d)));
        
    }
}
