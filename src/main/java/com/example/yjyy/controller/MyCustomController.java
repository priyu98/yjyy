package com.example.yjyy.controller;

import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.service.MyCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liuhaoqi
 * @Date 2020/3/30 17:50
 * @Version 1.0
 */

@RestController
@RequestMapping("MyCustomController")
public class MyCustomController {

    @Autowired
    private MyCustomService myCustomService;


    @AppToken
    @CrossOrigin
    @PostMapping("findAndUpdateOrerStatusByscheduleid")
    public WebRestResult findAndUpdateOrerStatusByscheduleid(String scheduleid){
        WebRestResult result = myCustomService.findAndUpdateOrerStatusByscheduleid(scheduleid);
        return result;
    }

}
