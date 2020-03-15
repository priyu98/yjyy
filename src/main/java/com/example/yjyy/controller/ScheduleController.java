package com.example.yjyy.controller;

import com.example.yjyy.entity.Schedule;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.SchedulePageResult;
import com.example.yjyy.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ScheduleController")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @AppToken
    @CrossOrigin
    @PostMapping("addSchedule")
    public WebRestResult addSchedule(@RequestBody Schedule schedule){
        WebRestResult result = scheduleService.addSchedule(schedule);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteSchedule")
    public WebRestResult deleteSchedule(String scheduleid){
        WebRestResult result = scheduleService.deleteSchedule(scheduleid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateSchedule")
    public WebRestResult updateSchedule(@RequestBody Schedule schedule){
        WebRestResult result = scheduleService.updateSchedule(schedule);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getScheduleList")
    public PageResult<SchedulePageResult> getScheduleList(String coursename,String starttime,String endtime,int page,int pagesize){
        PageResult<SchedulePageResult> result = scheduleService.getScheduleList(coursename,starttime,endtime,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getWeekScheduleList")
    public PageResult<List<SchedulePageResult>> getWeekScheduleList(String date){
        PageResult<List<SchedulePageResult>> result = scheduleService.getWeekScheduleList(date);
        return result;
    }
}
