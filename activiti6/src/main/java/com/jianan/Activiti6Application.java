package com.jianan;

import org.activiti.engine.RepositoryService;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activiti6Application {
    public static void main( String[] args ) {
        SpringApplication.run(Activiti6Application.class, args);
    }
}
