package com.example.yjyy.service.impl;

import com.example.yjyy.dao.CardMapper;
import com.example.yjyy.dao.CourseMapper;
import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.Card;
import com.example.yjyy.entity.PayCard;
import com.example.yjyy.entity.dto.CardDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.CardPageResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.service.CardService;
import com.example.yjyy.util.HttpUtils;
import com.example.yjyy.util.Tools;
import com.example.yjyy.util.UUIDUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private PayCardMapper payCardMapper;

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger("orderlog");

    @Override
    public WebRestResult addCard(Card card) {
        WebRestResult result = new WebRestResult();
        card.setCardid(UUIDUtil.randomUUID());
        card.setFlag("0");
        card.setCreatedate(new Date());
        CardDto cardDto = new CardDto();
        cardDto.setVenuelist(card.getVenueList());
        cardDto.setCourselist(card.getCourseList());
        cardDto.setCardid(card.getCardid());
        if(cardMapper.insert(card)==1){
            if(card.getVenueList().size()>0){
                cardMapper.insertCardVenue(cardDto);
            }
            if(card.getCourseList().size()>0){
                cardMapper.insertCardCourse(cardDto);
            }
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public WebRestResult deleteCard(String cardid) {
        WebRestResult result = new WebRestResult();
        Card card = new Card();
        card.setCardid(cardid);
        card.setFlag("1");
        if(cardMapper.updateByPrimaryKeySelective(card)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        return result;
    }

    @Override
    public WebRestResult updateCard(Card card) {
        WebRestResult result = new WebRestResult();
        card.setModifydate(new Date());
        cardMapper.deleteCardVenue(card.getCardid());
        cardMapper.deleteCardCourse(card.getCardid());
        CardDto cardDto = new CardDto();
        cardDto.setVenuelist(card.getVenueList());
        cardDto.setCourselist(card.getCourseList());
        cardDto.setCardid(card.getCardid());
        if(cardMapper.updateByPrimaryKeySelective(card)==1){
            if(card.getVenueList() != null && card.getVenueList().size()>0){
                cardMapper.insertCardVenue(cardDto);
            }
            if(card.getCourseList() != null && card.getCourseList().size()>0){
                cardMapper.insertCardCourse(cardDto);
            }
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public PageResult<CardPageResult> getCardList(String cardname, String cardtype, int page, int pagesize) {
        PageResult<CardPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<CardPageResult> list = cardMapper.getCardList(cardname,cardtype,begin,end,pagesize);
        if(list.size()>0){
            for(CardPageResult cardPageResult: list){
                cardPageResult.setCourseList(cardMapper.getCourseListByCardId(cardPageResult.getCardid()));
                cardPageResult.setVenueList(cardMapper.getVenueListByCardId(cardPageResult.getCardid()));
            }
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public WebRestResult payCard(PayCard payCard) {
        WebRestResult result = new WebRestResult();
        payCard.setPayid(UUIDUtil.randomUUID());
        payCard.setPayflag(1);
        //线上支付直接发卡，补充卡号、有效期、发卡时间、额度
        if(payCard.getCardid() != null){
            payCard.setPayflag(0);
            payCard.setCardno(Tools.date2Str(new Date(),"yyyyMMdd")+Tools.autoGenericCode(payCardMapper.countNumToday(Tools.date2Str(new Date(),"yyyy-MM-dd"))+1,4));
            payCard.setTerm(cardMapper.selectByPrimaryKey(payCard.getCardid()).getTerm());
            payCard.setGivetime(new Date());
            payCard.setQuota(cardMapper.selectByPrimaryKey(payCard.getCardid()).getQuota());
            /*微信支付逻辑暂时不用
            JSONObject jsonObject = new JSONObject(HttpUtils.wxUnifiedOrder(payCard.getPayid(),userMapper.selectByPrimaryKey(payCard.getUserid()).getOpenid(),cardMapper.selectByPrimaryKey(payCard.getCardid()).getPrice()));
            if(jsonObject.getString("return_code").equals("SUCCESS")){
                if(jsonObject.getString("result_code").equals("SUCCESS")){
                    result.setMessage(jsonObject.getString("prepay_id"));
                }
                else{
                    result.setResult(WebRestResult.FAILURE);
                    result.setMessage(jsonObject.getString("err_code_des"));
                    return result;
                }
            }
            else{
                result.setResult(WebRestResult.FAILURE);
                result.setMessage(jsonObject.getString("return_msg"));
                return result;
            }

             */
        }
        if(payCardMapper.insert(payCard)==1){
            //直接发卡微信提醒
            /*
            if(payCard.getCardid()!=null){
                String openid = userMapper.selectByPrimaryKey(payCard.getUserid()).getOpenid();
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
                }
            }
            */
            result.setResult(WebRestResult.SUCCESS);
            logger.info("会员卡购买["+"订单号:"+payCard.getPayid()+" 用户:"+payCard.getUserid()+" 会员卡:"+payCard.getCardid()+" 支付方式："+
                    payCard.getPaystatus()+" 金额:"+payCard.getPayment());
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("会员卡购买失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteUserCard(String payid) {
        WebRestResult result = new WebRestResult();
        PayCard payCard = payCardMapper.selectByPrimaryKey(payid);
        if(payCardMapper.deleteByPrimaryKey(payid)==1){
            result.setResult(WebRestResult.SUCCESS);
            logger.info("卡订单删除["+"订单号:"+payCard.getPayid()+" 用户:"+payCard.getUserid()+" 会员卡:"+payCard.getCardid()+" 支付方式："+
                    payCard.getPaystatus()+" 金额:"+payCard.getPayment());
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除会员卡订单失败");
        }
        return result;
    }

    @Override
    public WebRestResult giveCard(PayCard payCard) {
        WebRestResult result = new WebRestResult();
        payCard.setCardno(Tools.date2Str(new Date(),"yyyyMMdd")+Tools.autoGenericCode(payCardMapper.countNumToday(Tools.date2Str(new Date(),"yyyy-MM-dd"))+1,4));
        payCard.setTerm(cardMapper.selectByPrimaryKey(payCard.getCardid()).getTerm());
        payCard.setGivetime(new Date());
        payCard.setQuota(cardMapper.selectByPrimaryKey(payCard.getCardid()).getQuota());
        if(payCardMapper.updateByPrimaryKeySelective(payCard)==1){
            //线下支管理员手动发卡微信提醒
            String openid = userMapper.selectByPrimaryKey(payCard.getUserid()).getOpenid();
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
            }
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("发卡失败");
        }
        return result;
    }

    @Override
    public PageResult<MemberPageResult> getMemberList(String username, String teacher, String cardno,String paystatus, int page, int pagesize) {
        PageResult<MemberPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<MemberPageResult> list = payCardMapper.getMemberList(username,teacher,cardno,paystatus,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public WebRestResult changeCardStatus(String status, String payid) {
        WebRestResult result = new WebRestResult();
        if(payCardMapper.changeCardStatus(status,payid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        return result;
    }

    @Override
    public WebRestResult withdrawCard(String payid){
        WebRestResult result = new WebRestResult();
        if(payCardMapper.withdrawCard(payid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("退卡失败");
        }
        return result;
    }
}
