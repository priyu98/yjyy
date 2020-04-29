package com.example.yjyy.service.impl;

import com.example.yjyy.dao.MyCustomMapper;
import com.example.yjyy.dao.OrderMapper;
import com.example.yjyy.entity.Order;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.service.MyCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuhaoqi
 * @Date 2020/3/30 18:12
 * @Version 1.0
 */
@Service
public class MyCustomServiceImpl implements MyCustomService {

    @Autowired
    private MyCustomMapper myCustomMapper;

    @Override
    public WebRestResult findAndUpdateOrerStatusByscheduleid(String scheduleid) {
        List<Order> orderList = myCustomMapper.findOrerstatusByscheduleid(scheduleid);
        WebRestResult result = new WebRestResult();
//        Map maps = new HashMap<>();
        if(orderList.size()==0){
            result.setResult(WebRestResult.SUCCESS);
            result.setMessage("操作成功");
            return result;
        }
        for (Order order : orderList) {
            if (order.getOrderstatus().equals("1001")) {
                order.setOrderstatus("1002");
            }
            if (order.getOrderstatus().equals("1000")) {
                order.setOrderstatus("1003");
            }
        }
        int flag = myCustomMapper.updateOrderStatusList(orderList);
        if (flag > 0) {
            result.setResult(WebRestResult.SUCCESS);
            result.setMessage("根据id把order查出来，将状态1001改成1002，状态1000改成1003，操作成功");
        } else {
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("根据id把order查出来，将状态1001改成1002，状态1000改成1003，操作失败");
        }
      return result;
}
}
