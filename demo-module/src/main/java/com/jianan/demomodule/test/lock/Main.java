package com.jianan.demomodule.test.lock;

import java.util.Scanner;

/**
 * @Author:
 * @Date: 2024/3/5
 * @description
 **/
public class Main {
    public static void main(String[] args) {
        /*BusinessService businessService = new BusinessService();
        new Thread(()-> businessService.approve("1")).start();
        new Thread(()-> businessService.approve("2")).start();*/

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if("read".equals(next)){
            readList();
        }else if("write".equals(next)){
            writeList();
        }
    }
    
    
    public static void readList() {
        System.out.println("readList");
    }
    
    public static void writeList() {
        System.out.println("writeList");
    }
}
