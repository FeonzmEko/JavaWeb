package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);

    void add(Student student);

    @Select("select * from student where id = #{id}")
    Student queryById(Integer id);

    void update(Student student);

    void violation(Student student);

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("clazz")
    List<Map<String, Object>> countStudentCountData();
}
