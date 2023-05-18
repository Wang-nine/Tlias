package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //AOP类
public class LogAspect {

    //注入
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;

    //抽取切面表达式
//    @Pointcut("execution(* *.*(..))")
//    public void pt(){};

    @Around("@annotation(com.itheima.anno.Log))")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //操作人id - 当前登录人的id
        //获取请求头的jwt令牌解析id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //原始方法执行
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        //操作方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        return result;
    }
}
