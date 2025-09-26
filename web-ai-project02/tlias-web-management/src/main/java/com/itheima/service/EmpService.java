package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    public PageResult<Emp> page(EmpQueryParam empQueryParam);

    public void save(Emp emp);

    public void delete(List<Integer> ids);
}
