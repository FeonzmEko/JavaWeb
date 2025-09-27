package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询所有学生信息,{}",studentQueryParam);
        PageResult<Student> pageResult =  studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer>ids){
        log.info("删除员工：{}", ids);
        studentService.deleteById(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员：{}", student);
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("查询员工：{}", id);
        Student student = studentService.queryById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改员工信息：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("学生id：{}，扣分：{}", id, score);
        Student student = studentService.queryById(id);
        Short count = student.getViolationCount();
        count++;
        student.setViolationCount(count);
        Short score_temp = student.getViolationScore();
        score_temp = (short)(score_temp + score);
        student.setViolationScore(score_temp);
        studentService.violation(student);
        return Result.success();
    }
}
