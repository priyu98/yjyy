package com.example.yjyy.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 */
@Component
public class JwtUtils {

    private static final  Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //APP模块，是通过jwt认证的，如果要使用APP模块，则需要修改【加密秘钥】
    static final String secret = "03dfa0d863d5d5dc72dc513c870cacac[www.cs12345.com]";
    //token有效时长，7天，单位秒
    static final long expire = 604800;

    static final String header = "token";

    /**
     * 生成jwt token
     */
    public static String generateToken(Object userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId.toString())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public static String getSecret() {
        return secret;
    }

    public static long getExpire() {
        return expire;
    }

    public static String getHeader() {
        return header;
    }

}
