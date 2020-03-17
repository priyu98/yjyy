package com.example.yjyy.service.impl;

import com.example.yjyy.dao.ClassRoomMapper;
import com.example.yjyy.dao.OrderMapper;
import com.example.yjyy.dao.ScheduleMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.Schedule;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.OrderUserResult;
import com.example.yjyy.result.business.PageResult.SchedulePageResult;
import com.example.yjyy.service.ScheduleService;
import com.example.yjyy.util.HttpUtils;
import com.example.yjyy.util.Tools;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ClassRoomMapper classRoomMapper;

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
            schedule = scheduleMapper.selectByPrimaryKey(schedule.getScheduleid());
            List<OrderUserResult> list = orderMapper.getStudentList(schedule.getScheduleid());
            String access_token = userMapper.getAccessToken();
            String template_id = "QGKbbQxl2JIyswe8dKkUegv8gL3ccNGltDHGCe6nV8s";
            Map<String, Object> data = new HashMap<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            Map<String, String> map4 = new HashMap<>();
            Map<String, String> map5 = new HashMap<>();
            map1.put("value",schedule.getCoursename());
            map2.put("value",userMapper.selectByPrimaryKey(schedule.getCourseteacher()).getUsername());
            map3.put("value",Tools.date2Str(schedule.getStarttime(),"yyyy-MM-dd hh:mm"));
            map4.put("value",classRoomMapper.selectByPrimaryKey(schedule.getClassroom()).getClassroomname());
            map5.put("value","管理员取消课程");
            data.put("name1",map1);
            data.put("name2",map2);
            data.put("date3",map3);
            data.put("thing4",map4);
            data.put("thing5",map5);
            for(OrderUserResult user : list) {
                String openid = userMapper.selectByPrimaryKey(user.getUserid()).getOpenid();
                if(!"".equals(openid) && openid != null) {
                    HttpUtils.wxSendMsg(access_token,openid,template_id,data);
                }
            }
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
            schedule = scheduleMapper.selectByPrimaryKey(schedule.getScheduleid());
            List<OrderUserResult> list = orderMapper.getStudentList(schedule.getScheduleid());
            String access_token = userMapper.getAccessToken();
            String template_id = "vwXkFVzzhLyYCAAd3d87A4zwyuim_9asD73svlF7g_U";
            Map<String, Object> data = new HashMap<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            Map<String, String> map4 = new HashMap<>();
            Map<String, String> map5 = new HashMap<>();
            map1.put("value",schedule.getCoursename());
            map2.put("value",userMapper.selectByPrimaryKey(schedule.getCourseteacher()).getUsername());
            map3.put("value",Tools.date2Str(schedule.getStarttime(),"yyyy-MM-dd hh:mm"));
            map4.put("value",classRoomMapper.selectByPrimaryKey(schedule.getClassroom()).getClassroomname());
            map5.put("value","您预约的课程发生了变更");
            data.put("name1",map1);
            data.put("name2",map2);
            data.put("date3",map3);
            data.put("thing4",map4);
            data.put("thing5",map5);
            for(OrderUserResult user : list) {
                String openid = userMapper.selectByPrimaryKey(user.getUserid()).getOpenid();
                if(!"".equals(openid) && openid != null) {
                    HttpUtils.wxSendMsg(access_token,openid,template_id,data);
                }
            }
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
