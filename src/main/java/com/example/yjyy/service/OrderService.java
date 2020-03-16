package com.example.yjyy.service;

import com.example.yjyy.entity.User;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.OrderUserResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.result.business.PageResult.OrderPageResult;
import com.example.yjyy.result.business.PageResult.OrderRecordPageResult;

import java.util.List;

public interface OrderService {
    WebRestResult addOrder(String userid,String scheduleid,String payid);
    WebRestResult cancelOrder(String orderid);
    PageResult<OrderPageResult> getOrderList(String userid,String scheduleid,String orderstatus,int page,int pagesize);
    PageResult<MemberPageResult> getUseableCards(String userid,String scheduleid);
    WebRestResult checkin(List<String> orderlist);
    PageResult<OrderUserResult> getStudentList(String scheduleid);
    PageResult<OrderRecordPageResult> getOrderRecordList(String userid,int page,int pagesize);
}
