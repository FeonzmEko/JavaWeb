package com.itheima.controller;

import com.itheima.exception.IsHaveStudentExecption;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询所有班级信息,{}",clazzQueryParam);
        PageResult<Clazz> pageResult =  clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级信息：{}", id);
        int i = clazzService.deleteById(id);
        if(i == 0){
            throw new IsHaveStudentExecption();
        }
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级信息：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("查询班级信息：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

}
