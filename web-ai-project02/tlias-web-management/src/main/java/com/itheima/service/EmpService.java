package com.itheima.service;

import com.itheima.pojo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    public PageResult<Emp> page(EmpQueryParam empQueryParam);

    public void save(Emp emp);

    public void delete(List<Integer> ids);

    public Emp getInfo(Integer id);

    public void update(Emp emp);

    LoginInfo login(Emp emp);
}
