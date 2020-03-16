package com.example.yjyy.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.example.yjyy.dao.OrderMapper;
import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.entity.Order;
import com.example.yjyy.entity.PayCard;
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
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PayCardMapper payCardMapper;

    @Override
    public WebRestResult addOrder(String userid, String scheduleid, String payid) {
        WebRestResult result = new WebRestResult();
        Order order = new Order(UUIDUtil.randomUUID(),userid,scheduleid,new Date(),"1",null,"1000",payid);
        if(orderMapper.insert(order)==1){
            PayCard payCard = payCardMapper.selectByPrimaryKey(payid);
            if(payCard.getCardstatus().equals("0")){
                payCard.setCardstatus("1");
                payCard.setQuota(payCard.getQuota()-1);
                payCardMapper.updateByPrimaryKeySelective(payCard);
            }
            else{
                payCard.setQuota(payCard.getQuota()-1);
                payCardMapper.updateByPrimaryKeySelective(payCard);
            }
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("预约成功");
        return result;
    }

    @Override
    public WebRestResult cancelOrder(String orderid) {
        WebRestResult result = new WebRestResult();
        Order order = new Order(orderid,null,null,null,null,null,"3000",null);
        if(orderMapper.updateByPrimaryKeySelective(order)==1){
            result.setResult(WebRestResult.SUCCESS);
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

       List<OrderPageResult> list = orderMapper.getOrderList(userid,scheduleid,orderstatus,begin,end,pagesize);
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
            result.setResult(WebRestResult.SUCCESS);
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
