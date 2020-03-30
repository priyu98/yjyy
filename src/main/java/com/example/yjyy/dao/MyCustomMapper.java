package com.example.yjyy.dao;

import com.example.yjyy.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author liuhaoqi
 * @Date 2020/3/30 21:26
 * @Version 1.0
 */
@Component
public interface MyCustomMapper {
    //根据scheduleid找出数据
    List<Order> findOrerstatusByscheduleid(@Param("scheduleid") String scheduleid);
    //根据id把order查出来，然后判断状态执行不同的update
    int updateOrderStatusList(List orderList);
}
