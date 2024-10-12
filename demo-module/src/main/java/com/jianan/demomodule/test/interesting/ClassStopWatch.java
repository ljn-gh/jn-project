package com.jianan.demomodule.test.interesting;




import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @Author: jn
 * @Date: 2024/8/30
 * @description
 **/
public class ClassStopWatch {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("方法执行--------");
        stopWatch.start();
        TimeUnit.SECONDS.sleep(5);
        stopWatch.stop();
        System.out.println();
    }
}
