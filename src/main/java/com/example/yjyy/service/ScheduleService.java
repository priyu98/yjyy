package com.example.yjyy.service;

import com.example.yjyy.entity.Schedule;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.SchedulePageResult;

import java.util.List;

public interface ScheduleService {
    WebRestResult addSchedule(Schedule schedule);
    WebRestResult deleteSchedule(String scheduleid);
    WebRestResult updateSchedule(Schedule schedule);
    PageResult<SchedulePageResult> getScheduleList(String coursename,String starttime,String endtime,int page,int pagesize,String userid);
    PageResult<List<SchedulePageResult>> getWeekScheduleList(String date);
}
