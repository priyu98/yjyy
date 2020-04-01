package com.example.yjyy.service;

import com.example.yjyy.result.WebRestResult;

import java.util.List;

/**
 * @Author liuhaoqi
 * @Date 2020/3/30 18:12
 * @Version 1.0
 */
public interface MyCustomService {
    WebRestResult findAndUpdateOrerStatusByscheduleid(String scheduleid);
}
