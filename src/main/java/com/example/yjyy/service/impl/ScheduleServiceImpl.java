package com.example.yjyy.service.impl;

import com.example.yjyy.dao.ScheduleMapper;
import com.example.yjyy.entity.Schedule;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.SchedulePageResult;
import com.example.yjyy.service.ScheduleService;
import com.example.yjyy.util.Tools;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public WebRestResult addSchedule(Schedule schedule) {
        WebRestResult result = new WebRestResult();
        schedule.setScheduleid(UUIDUtil.randomUUID());
        schedule.setCreatedate(new Date());
        schedule.setFlag("0");
        if(scheduleMapper.insert(schedule)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("排课失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteSchedule(String scheduleid) {
        WebRestResult result = new WebRestResult();
        Schedule schedule = new Schedule();
        schedule.setScheduleid(scheduleid);
        schedule.setFlag("1");
        if(scheduleMapper.updateByPrimaryKeySelective(schedule)==1){
            scheduleMapper.cancelOrderByAdmin(scheduleid);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("课程取消失败");
        }
        return result;
    }

    @Override
    public WebRestResult updateSchedule(Schedule schedule) {
        WebRestResult result = new WebRestResult();
        schedule.setModifydate(new Date());
        if(scheduleMapper.updateByPrimaryKeySelective(schedule)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("更新排课失败");
        }
        return result;
    }

    @Override
    public PageResult<SchedulePageResult> getScheduleList(String coursename, String starttime, String endtime, int page, int pagesize) {
        PageResult<SchedulePageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<SchedulePageResult> list = scheduleMapper.getScheduleList(coursename,starttime,endtime,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("获取排课列表成功");
        return result;
    }

    @Override
    public PageResult<List<SchedulePageResult>> getWeekScheduleList(String date) {
        PageResult<List<SchedulePageResult>> result = new PageResult<>();
        int begin = 0;
        int end = 100;
        String starttime = Tools.getSundayDate(Tools.str2Date1(date));
        String endtime = Tools.date2Str(new Date(Tools.str2Date1(starttime).getTime()+7*24*60*60*1000),"yyyy-MM-dd");

        List<SchedulePageResult> list = scheduleMapper.getScheduleList("",starttime,endtime,begin,end,100);
        List<List<SchedulePageResult>> weekList = new ArrayList<>();
        for(int i=1;i<8;i++){
            List<SchedulePageResult> tempList = new ArrayList<>();
            for(SchedulePageResult schedulePageResult: list){
                if(Tools.getWeekDay(schedulePageResult.getStarttime())==i)
                    tempList.add(schedulePageResult);
            }
            weekList.add(tempList);
        }
        if(list.size()>0){
            result.setRows(weekList);
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("获取周排课列表成功");
        return result;
    }
}
