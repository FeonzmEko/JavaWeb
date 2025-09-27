package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentserviceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMappper;
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> studentList = studentMappper.list(studentQueryParam);

        Page<Student>p = (Page<Student>)studentList;

        return new PageResult<Student>(p.getTotal(),studentList);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        studentMappper.deleteById(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMappper.add(student);
    }

    @Override
    public Student queryById(Integer id) {
        return studentMappper.queryById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMappper.update(student);
    }

    @Override
    public void violation(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMappper.violation(student);
    }
}
