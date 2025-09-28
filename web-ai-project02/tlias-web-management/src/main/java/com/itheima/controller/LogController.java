package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log/page")
    public Result page(LogQueryParam logQueryParam){
        log.info("查询所有日志信息,{}",logQueryParam);
        PageResult<LogInformation> pageResult =  logService.page(logQueryParam);
        return Result.success(pageResult);
    }
}
