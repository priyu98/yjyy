package com.example.yjyy.controller;

import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.Schedule;
import com.example.yjyy.util.HttpUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskController {
    @Autowired
    private PayCardMapper payCardMapper;

    @Autowired
    private UserMapper userMapper;

    private final String appid = "wxf275b0bcada2b33b";
    private final String secret = "f9088de1385f71c020c8fc436a5ea072";

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void autoCardTerm(){
        try{
            payCardMapper.autoExpire();
            payCardMapper.autoReduceTerm();
            payCardMapper.autoOpenCard();
            payCardMapper.autoReduceOpenTerm();
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Scheduled(cron = "0 43 0/1 * * ?")
    public void updateAccessToken(){
        Map<String,String> param = new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        param.put("grant_type","client_credential");
        String result = HttpUtils.doGet("https://api.weixin.qq.com/cgi-bin/token?",param);
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        if(jsonObject.has("access_token"))
            userMapper.updateAccessToken(jsonObject.getString("access_token"));
    }

}
