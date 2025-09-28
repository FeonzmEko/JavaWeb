package com.itheima.mapper;

import com.itheima.pojo.LogInformation;
import com.itheima.pojo.LogQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    List<LogInformation> list(LogQueryParam logQueryParam);
}
