package com.jianan.demomodule.test.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jn
 * @Date: 2024/4/19
 * @description
 **/
public class DateFormatTest {
    public static void main(String[] args) {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date1 = Date.from(LocalDateTime.of(2024,04,19,15,56,00).atZone(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDateTime.of(2024,05,19,15,56,00).atZone(ZoneId.systemDefault()).toInstant());
        Date date3 = Date.from(LocalDateTime.of(2024,06,19,15,56,00).atZone(ZoneId.systemDefault()).toInstant());
        new Thread(()->{
            
            System.out.println(Thread.currentThread().getName() + "-------" + date1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "-------" + s.format(date1));
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-------" + date2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "-------" + s.format(date2));
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "-------" + date3);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "-------" + s.format(date3));
        }).start();
    }
}
