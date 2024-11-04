package com.jianan.demomodule;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
@MapperScan(
        basePackages = "com.jianan.demomodule.mapper"
)
@EnableCaching
public class DemoModuleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoModuleApplication.class, args);
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.getConnection().createStatement().execute("select 1 from dual");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
