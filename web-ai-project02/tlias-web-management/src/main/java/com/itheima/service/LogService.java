package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.LogInformation;
import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.PageResult;

public interface LogService {
    PageResult<LogInformation> page(LogQueryParam logQueryParam);
}
