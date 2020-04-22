package com.example.yjyy.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.example.yjyy.dao.*;
import com.example.yjyy.entity.Order;
import com.example.yjyy.entity.PayCard;
import com.example.yjyy.entity.Schedule;
import com.example.yjyy.entity.User;
import com.example.yjyy.entity.dto.CheckinDto;
import com.example.yjyy.entity.dto.UserDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.OrderUserResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.result.business.PageResult.OrderPageResult;
import com.example.yjyy.result.business.PageResult.OrderRecordPageResult;
import com.example.yjyy.service.OrderService;
import com.example.yjyy.util.HttpUtils;
import com.example.yjyy.util.Tools;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PayCardMapper payCardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ClassRoomMapper classRoomMapper;

    @Override
    public WebRestResult addOrder(String userid, String scheduleid, String payid) {
        WebRestResult result = new WebRestResult();
        Order order = new Order(UUIDUtil.randomUUID(),userid,scheduleid,new Date(),"1",null,"1000",payid);
        PayCard payCard = payCardMapper.selectByPrimaryKey(payid);
        Schedule schedule = scheduleMapper.selectByPrimaryKey(scheduleid);
        if(payCard.getQuota()==orderMapper.countOrderByUserid(userid,"1000")){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("预约课程数量已超过卡剩余额度，预约失败");
            return result;
        }
        if(schedule.getStarttime().compareTo(new Date(new Date().getTime()+60*60*1000))<0){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("距课程开始不足一小时，预约已结束");
            return result;
        }
        if(orderMapper.insert(order)==1){
            if(payCard.getCardstatus().equals("0")){
                payCard.setCardstatus("1");
                payCardMapper.updateByPrimaryKeySelective(payCard);
            }
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("预约成功");
        String access_token = userMapper.getAccessToken();
        String template_id = "7RDWriJThlCrK9KtiWnLsex1GDCUJnn7DGHoiSdazUI";
        String openid = userMapper.selectByPrimaryKey(userid).getOpenid();
        if(openid != null && !"".equals(openid)) {
            Map<String, Object> data = new HashMap<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            Map<String, String> map4 = new HashMap<>();
            map1.put("value", schedule.getCoursename());
            map2.put("value", userMapper.selectByPrimaryKey(schedule.getCourseteacher()).getUsername());
            map3.put("value", Tools.date2Str(schedule.getStarttime(), "yyyy-MM-dd hh:mm"));
            map4.put("value", classRoomMapper.selectByPrimaryKey(schedule.getClassroom()).getClassroomname());
            data.put("thing6", map1);
            data.put("name2", map2);
            data.put("time3", map3);
            data.put("thing4", map4);
            HttpUtils.wxSendMsg(access_token,openid,template_id,data);
        }
        return result;
    }

    @Override
    public WebRestResult cancelOrder(String orderid) {
        WebRestResult result = new WebRestResult();
        Order order = new Order(orderid,null,null,null,null,null,"3000",null);
        if(orderMapper.updateByPrimaryKeySelective(order)==1){
            result.setResult(WebRestResult.SUCCESS);
            String access_token = userMapper.getAccessToken();
            String template_id = "QGKbbQxl2JIyswe8dKkUegv8gL3ccNGltDHGCe6nV8s";
            Map<String, Object> data = new HashMap<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            Map<String, String> map4 = new HashMap<>();
            Map<String, String> map5 = new HashMap<>();
            order = orderMapper.selectByPrimaryKey(orderid);
            String openid = userMapper.selectByPrimaryKey(order.getUserid()).getOpenid();
            if(!"".equals(openid) && openid != null) {
                Schedule schedule = scheduleMapper.selectByPrimaryKey(order.getScheduleid());
                map1.put("value", schedule.getCoursename());
                map2.put("value", userMapper.selectByPrimaryKey(schedule.getCourseteacher()).getUsername());
                map3.put("value", Tools.date2Str(schedule.getStarttime(), "yyyy-MM-dd hh:mm"));
                map4.put("value", classRoomMapper.selectByPrimaryKey(schedule.getClassroom()).getClassroomname());
                map5.put("value", "用户取消");
                data.put("name1", map1);
                data.put("name2", map2);
                data.put("time3", map3);
                data.put("thing4", map4);
                data.put("thing5", map5);
                HttpUtils.wxSendMsg(access_token, openid, template_id, data);
            }
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("取消预约失败");
        }
        return result;
    }

    @Override
    public PageResult<OrderPageResult> getOrderList(String userid, String scheduleid, String orderstatus,int page,int pagesize) {
       PageResult<OrderPageResult> result = new PageResult<>();
       int begin = (page-1) * pagesize;
       int end = pagesize;
       List<OrderPageResult> list = new ArrayList<>();
       if(!Tools.isEmpty(orderstatus) && orderstatus.equals("0"))
           list = orderMapper.getUnfinishedOrderList(userid,scheduleid,begin,end,pagesize);
       else if(!Tools.isEmpty(orderstatus) && orderstatus.equals("1"))
           list = orderMapper.getFinishedOrderList(userid,scheduleid,begin,end,pagesize);
       else
           list = orderMapper.getOrderList(userid,scheduleid,orderstatus,begin,end,pagesize);
       if(list.size()>0){
           result.setTotal(list.get(0).getCnt());
           result.setPageCount(list.get(0).getPage());
           result.setRows(list);
       }
       result.setResult(WebRestResult.SUCCESS);
       return result;
    }

    @Override
    public PageResult<MemberPageResult> getUseableCards(String userid, String scheduleid) {
        PageResult<MemberPageResult> result = new PageResult<>();
        List<MemberPageResult> list = payCardMapper.getUseableCards(userid,scheduleid);
        if(list.size()>0){
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public WebRestResult checkin(List<String> orderlist) {
        WebRestResult result = new WebRestResult();
        CheckinDto checkinDto = new CheckinDto();
        checkinDto.setDate(new Date());
        checkinDto.setOrderlist(orderlist);
        if(orderMapper.checkin(checkinDto)==orderlist.size()){
            for(String orderid : orderlist){
                PayCard payCard = payCardMapper.selectByPrimaryKey(orderMapper.selectByPrimaryKey(orderid).getPayid());
                payCard.setQuota(payCard.getQuota()-1);
                payCardMapper.updateByPrimaryKeySelective(payCard);
            }
            result.setResult(WebRestResult.SUCCESS);
            //消费成功微信提醒
            String access_token = userMapper.getAccessToken();
            String template_id = "6lNFHALrZnjI_ottaUlfoj9ZmPSLMZlfTcpVM0ssbaY";
            Map<String, Object> data = new HashMap<>();
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            Map<String, String> map4 = new HashMap<>();
            for(String orderid : orderlist) {
                Order order = orderMapper.selectByPrimaryKey(orderid);
                String openid = userMapper.selectByPrimaryKey(order.getUserid()).getOpenid();
                if(!"".equals(openid) && openid != null){
                    Schedule schedule = scheduleMapper.selectByPrimaryKey(order.getScheduleid());
                    map1.put("value",schedule.getCoursename());
                    map2.put("value",userMapper.selectByPrimaryKey(schedule.getCourseteacher()).getUsername());
                    map3.put("value", Tools.date2Str(schedule.getStarttime(),"yyyy-MM-dd hh:mm"));
                    map4.put("value",classRoomMapper.selectByPrimaryKey(schedule.getClassroom()).getClassroomname());
                    data.put("name1",map1);
                    data.put("name2",map2);
                    data.put("date3",map3);
                    data.put("thing4",map4);
                    HttpUtils.wxSendMsg(access_token,openid,template_id,data);
                }
            }
        }
        else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("签到失败");
        }
        return result;
    }

    @Override
    public PageResult<OrderUserResult> getStudentList(String scheduleid) {
        PageResult<OrderUserResult> result = new PageResult<>();
        List<OrderUserResult> list = orderMapper.getStudentList(scheduleid);
        if(list.size()>=0){
            result.setTotal(list.size());
            result.setRows(list);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
        }
        return result;
    }

    @Override
    public PageResult<OrderRecordPageResult> getOrderRecordList(String userid,int page,int pagesize){
        PageResult<OrderRecordPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        UserDto userDto = new UserDto(userid,begin,end,pagesize);
        List<OrderRecordPageResult> list = orderMapper.getOrderRecordList(userDto);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }
}
