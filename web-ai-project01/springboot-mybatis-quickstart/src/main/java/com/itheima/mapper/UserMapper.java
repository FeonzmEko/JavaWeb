package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会自动的为接口创建一个实现类对象，并且会自动将该实现类对象存入IOC容器
public interface UserMapper {

    /*
    * 查询所有用户
    * */
    //@Select("select * from user")
    public List<User> findAll();

    //@Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into user(id, username, password, name, age) values (#{id},#{username},#{password},#{name},#{age})")
    public void insertUser(User user);

    @Update("UPDATE user SET name = #{name2} WHERE name = #{name1}")
    public void update(@Param("name1") String name1, @Param("name2") String name2);

    @Select("select  * from user where username = #{username} and password = #{password}")
    public void findUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
