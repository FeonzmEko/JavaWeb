package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperateLogAspect {

    @Autowired
    private final OperateLogMapper operateLogMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 切点：拦截 controller 包下的所有 增删改 方法
     * 假设规则是：方法名以 add/save/create/update/edit/delete/remove 开头
     */
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 目标类和方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodSignature.getName();

        // 方法参数序列化
        String methodParams = "";
        try {
            methodParams = objectMapper.writeValueAsString(joinPoint.getArgs());
        } catch (Exception e) {
            methodParams = "参数序列化失败";
        }

        Object result = null;
        String returnValue = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            // 返回值序列化
            try {
                returnValue = objectMapper.writeValueAsString(result);
            } catch (Exception e) {
                returnValue = "返回值序列化失败";
            }
            return result;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;

            // 模拟获取当前登录用户ID（实际项目应从 Session / SecurityContext / ThreadLocal 获取）
            Integer operateEmpId = getCurrentUserId();

            // 记录日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(returnValue);
            operateLog.setCostTime(costTime);

            // 保存到数据库
            operateLogMapper.insert(operateLog);

            log.info("操作日志已记录: {}", operateLog);
        }
    }

    /**
     * 模拟获取当前登录用户ID
     * 实际开发中可从 ThreadLocal / Spring Security / JWT 中取出
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId(); // 假设当前用户ID为1
    }
}
