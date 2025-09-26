package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImp implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    // 原始分页查询
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize,
                                String name, Integer gender,
                                LocalDateTime begin, LocalDateTime end) {
        // 1.获取总记录数
        Long count = empMapper.count();

        // 2.获取每一页的数据列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.list(start, pageSize,name,gender,begin,end);

        return new PageResult<Emp>(count,empList);
    }*/
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1.设置分页参数(pageHelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), empList);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(e->e.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

}
