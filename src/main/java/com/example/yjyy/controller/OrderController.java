package com.example.yjyy.controller;

import com.example.yjyy.entity.User;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.result.business.PageResult.OrderPageResult;
import com.example.yjyy.result.business.PageResult.OrderRecordPageResult;
import com.example.yjyy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("OrderController")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @AppToken
    @CrossOrigin
    @PostMapping("addOrder")
    public WebRestResult addOrder(String userid,String scheduleid,String payid){
        WebRestResult result = orderService.addOrder(userid,scheduleid,payid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("cancelOrder")
    public WebRestResult cancelOrder(String orderid){
        WebRestResult result = orderService.cancelOrder(orderid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getOrderList")
    public PageResult<OrderPageResult> getOrderList(String userid,String scheduleid,String orderstatus,int page,int pagesize){
        PageResult<OrderPageResult> result = orderService.getOrderList(userid,scheduleid,orderstatus,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getUseableCards")
    public PageResult<MemberPageResult> getUseableCards(String userid,String scheduleid){
        PageResult<MemberPageResult> result = orderService.getUseableCards(userid,scheduleid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("checkin")
    public WebRestResult chekin(@RequestBody List<String> orderlist){
        WebRestResult result = orderService.checkin(orderlist);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getStudentList")
    public PageResult<User> getStudentList(String scheduleid){
        PageResult<User> result = orderService.getStudentList(scheduleid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getOrderRecordList")
    public PageResult<OrderRecordPageResult> getOrderRecordList(String userid,int page,int pagesize){
        PageResult<OrderRecordPageResult> result = orderService.getOrderRecordList(userid,page,pagesize);
        return result;
    }
}
