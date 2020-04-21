package com.example.yjyy.config;


import org.json.JSONObject;
import com.example.yjyy.result.WebRestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * ..表示包及子包 该方法代表controller层的所有方法  TODO 路径需要根据自己项目定义
     */
    @Pointcut("execution(public * com.example.yjyy.controller..*.*(..)) && !execution(public * com.example.yjyy.controller.TaskController.*(..))")
    public void controllerMethod() {
    }


    /**
     * 方法执行前
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder requestLog = new StringBuilder();
        Signature signature = joinPoint.getSignature();
        requestLog.append("requestURL: ").append("URL = {").append(request.getRequestURI()).append("},\t")
                .append("requestMethod = {").append(request.getMethod()).append("},\t")
                .append("requestIP = {").append(request.getRemoteAddr()).append("},\t")
                .append("classMethod = {").append(signature.getDeclaringTypeName()).append(".")
                .append(signature.getName()).append("},\t");

        // 处理请求参数
        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0) {
            requestLog.append("requestParam = {} ");
        } else {
            requestLog.append("requestParam = [");
            for (int i = 0; i < paramLength - 1; i++) {
                requestLog.append(paramNames[i]).append("=").append(JSONObject.valueToString(paramValues[i])).append(",");
            }
            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.valueToString(paramValues[paramLength - 1])).append("]");
        }

        log.info(requestLog.toString());
    }


    /**
     * 方法执行后
     *
     * @param webRestResult
     * @throws Exception
     */
    @AfterReturning(returning = "webRestResult", pointcut = "controllerMethod()")
    public void logResultVOInfo(WebRestResult webRestResult) throws Exception {
        log.info("requestResult: " + webRestResult.getResult() + "\t" + webRestResult.getMessage());
    }


}
