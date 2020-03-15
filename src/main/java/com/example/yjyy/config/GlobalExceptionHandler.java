package com.example.yjyy.config;

import com.example.yjyy.result.WebRestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description:全局异常处理类
 * @Author:HuangXuanxiang
 * @Date: 2019/7/29
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public WebRestResult exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
//        LOGGER.error(e.getMessage());
        LOGGER.error(errInfo(e));
        WebRestResult result = new WebRestResult();
        result.setMessage(e.getMessage());
        result.setResult(WebRestResult.FAILURE);
        return result;
    }

    public static String errInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
