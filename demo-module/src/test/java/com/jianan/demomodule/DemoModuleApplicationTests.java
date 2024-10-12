package com.jianan.demomodule;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoModuleApplicationTests {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Test
    void contextLoads() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        boolean execute = sqlSession.getConnection().createStatement().execute("select 1 from dual");
        System.out.println(execute);
    }

}
