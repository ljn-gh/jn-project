package com.jianan.demomodule.test.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @Author: jn
 * @Date: 2024/7/22
 * @description
 **/
@Component
@Slf4j
public class XxlJobTest {
    @XxlJob(value = "test1")
    public void test1(){
        log.info("test1---开始执行");
        XxlJobHelper.log("test1---执行成功!");
    }
}
