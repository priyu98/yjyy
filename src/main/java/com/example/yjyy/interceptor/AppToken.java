package com.example.yjyy.interceptor;

import java.lang.annotation.*;

/**
 * app效验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppToken {
}
