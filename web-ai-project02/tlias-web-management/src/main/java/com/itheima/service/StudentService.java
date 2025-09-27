package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);

    void add(Student student);

    Student queryById(Integer id);

    void update(Student student);

    void violation(Student student);
}
