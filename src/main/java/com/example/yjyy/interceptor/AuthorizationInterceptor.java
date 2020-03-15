package com.example.yjyy.interceptor;


import com.example.yjyy.result.RxException;
import com.example.yjyy.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_KEY = "username";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppToken annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AppToken.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(JwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(JwtUtils.getHeader());
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new RxException(JwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        Claims claims = JwtUtils.getClaimByToken(token);
        if(claims == null || JwtUtils.isTokenExpired(claims.getExpiration())){
            throw new RxException(JwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        // String username = claims.getSubject();
        // if (claims == null) {
        //     throw new RxException("验证错误", HttpStatus.UNAUTHORIZED.value());
        // }
        // String id = appMemberDao.queryIdByUserName(username);
        // if (id == null) {
        //     throw new RxException("验证错误", HttpStatus.UNAUTHORIZED.value());
        // } else {
        //     return true;
        // }
        //设置userId到request里，后续根据userId，获取用户信息
//        request.setAttribute(USER_KEY, Long.parseLong(claims.getSubject()));

       return true;
    }
}
