package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*@Results({
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })*/

    @Select("select * from dept order by update_time desc;")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept (id, name, create_time, update_time ) values (#{id}, #{name}, #{createTime},#{updateTime})")
    public void add(Dept dept);

    @Select("select * from dept where id = #{id}")
    public Dept getById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);
}
