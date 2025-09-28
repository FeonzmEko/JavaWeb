package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("出错了，请联系管理员~~~");
    }*/

    @ExceptionHandler
    public Result handleDuplicateException(DuplicateKeyException d){
        log.error("程序出错了",d);
        return Result.error("手机号已存在!");
    }

    @ExceptionHandler
    public Result handleIsHaveStudentException(IsHaveStudentExecption i){
        log.error("程序出错了",i);
        return Result.error("班内仍有学生，不能删除！");
    }

    @ExceptionHandler
    public Result handleIsHaveDeptException(IsHaveDeptException i){
        log.error("程序出错了",i);
        return Result.error("部门内仍有员工，不能删除！");
    }

    @ExceptionHandler
    public Result handleIsNotHaveEmp(IsNotHaveEmp i){
        log.error("程序出错了");
        return Result.error("员工信息有误，请重新输入！");
    }

}
