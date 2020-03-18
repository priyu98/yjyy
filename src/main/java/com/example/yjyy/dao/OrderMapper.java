package com.example.yjyy.dao;

import com.example.yjyy.entity.Order;
import com.example.yjyy.entity.User;
import com.example.yjyy.entity.dto.CheckinDto;
import com.example.yjyy.entity.dto.UserDto;
import com.example.yjyy.result.business.OrderUserResult;
import com.example.yjyy.result.business.PageResult.OrderPageResult;
import com.example.yjyy.result.business.PageResult.OrderRecordPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderPageResult> getOrderList(@Param("userid") String userid,
                                       @Param("scheduleid") String scheduleid,
                                       @Param("orderstatus") String orderstatus,
                                       @Param("begin") int begin,
                                       @Param("end") int end,
                                       @Param("pagesize") int pagesize);

    int changeOrderStatus(@Param("status") String status,
                          @Param("orderid") String orderid);

    int checkin(CheckinDto checkinDto);

    List<OrderUserResult> getStudentList(String scheduleid);
    //获取消费记录
    List<OrderRecordPageResult> getOrderRecordList(UserDto userDto);
    String findOrderByUserSchedule(@Param("userid") String userid,
                                   @Param("scheduleid") String scheduleid);
}