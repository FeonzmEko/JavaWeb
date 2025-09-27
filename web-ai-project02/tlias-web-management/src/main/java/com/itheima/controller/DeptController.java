package com.itheima.controller;

import com.itheima.exception.IsHaveDeptException;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    @RequestMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(Integer id){
        log.info("根据ID删除部门：" + id);
        int i = deptService.deleteById(id);
        if(i == 0){
            throw new IsHaveDeptException();
        }
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    // 根据id查询部门
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询部门：" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    // 修改部门
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
