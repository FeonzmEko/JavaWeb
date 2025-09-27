package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ReportService {
    public JobOption getEmpJobData();

    public List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
