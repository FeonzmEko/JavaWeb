package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    @Select("select c.*,e.name masterName from clazz c left join emp e on c.master_id = e.id")
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    int deleteById(Integer id);

    void add(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz queryById(Integer id);

    void update(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> findAll();
}
