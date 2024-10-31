package com.jianan.demomodule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jianan.demomodule.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/10/25
 * @description
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Delete("delete from user where id = 'jn'")
    void delete();
    
   /* @Insert("insert into user(id,name) values('jn','jn')")
    void insert();*/
    
    @Select("select * from user where id = #{id} ")
    Object selectUserById(String id);
    
    List<User> selectAll();
}
