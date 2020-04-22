package com.example.yjyy.controller;

import com.example.yjyy.dao.CardMapper;
import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.PayCard;
import com.example.yjyy.util.HttpUtils;
import com.example.yjyy.util.Tools;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wxController")
public class wxController {

    private final Logger logger = LoggerFactory.getLogger("orderlog");

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PayCardMapper payCardMapper;

    @Autowired
    private CardMapper cardMapper;

    @PostMapping("wxReturnOrder")
    @CrossOrigin
    public void wxReturnOrder(HttpServletRequest request){
        String xmlStr = Tools.getWxXml(request);
        Map map = Tools.doXMLParse(xmlStr);
        String return_code = (String) map.get("return_code");
        if(return_code.equals("SUCCESS")){
            String openid = (String)map.get("openid");
            PayCard payCard = payCardMapper.selectByPrimaryKey((String)map.get("out_trade_no"));
            payCard.setPayflag(1);
            payCardMapper.updateByPrimaryKeySelective(payCard);
            if(openid != null && !"".equals(openid)) {
                String access_token = userMapper.getAccessToken();
                String template_id = "wmFTYifkYlVwHu8hiBW6p6IXJzNT6zVow0eT56qRKxY";
                String cardname = cardMapper.selectByPrimaryKey(payCard.getCardid()).getCardname();
                String starttime = Tools.date2Str(new Date(), "yyyy-MM-dd");
                Map<String, Object> data = new HashMap<>();
                Map<String, String> map1 = new HashMap<>();
                Map<String, String> map2 = new HashMap<>();
                map1.put("value", cardname);
                map2.put("value", starttime);
                data.put("thing1", map1);
                data.put("date2", map2);
                HttpUtils.wxSendMsg(access_token, openid, template_id, data);
                logger.info("会员卡支付成功["+"订单号:"+payCard.getPayid()+" 用户:"+payCard.getUserid()+" 会员卡:"+payCard.getCardid()+" 支付方式："+
                        payCard.getPaystatus()+" 金额:"+payCard.getPayment());
            }
        }
    }
}
