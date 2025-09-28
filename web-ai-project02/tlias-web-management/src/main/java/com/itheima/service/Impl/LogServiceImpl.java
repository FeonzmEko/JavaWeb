package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.Log;
import com.itheima.mapper.LogMapper;
import com.itheima.pojo.*;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.Inflater;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<LogInformation> page(LogQueryParam logQueryParam) {
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());
        List<LogInformation> logList = logMapper.list(logQueryParam);

        Page<LogInformation> p = (Page<LogInformation>)logList;

        return new PageResult<LogInformation>(p.getTotal(),logList);

    }
}
