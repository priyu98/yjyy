package com.example.yjyy.dao;

import com.example.yjyy.entity.PayCard;
import com.example.yjyy.result.business.CardOrderResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCardMapper {
    int deleteByPrimaryKey(String payid);

    int insert(PayCard record);

    int insertSelective(PayCard record);

    PayCard selectByPrimaryKey(String payid);

    int updateByPrimaryKeySelective(PayCard record);

    int updateByPrimaryKey(PayCard record);

    int countNumToday(String today);

    List<MemberPageResult> getMemberList(@Param("username") String username,
                                         @Param("teacher") String teacher,
                                         @Param("cardno") String cardno,
                                         @Param("paystatus") String paystatus,
                                         @Param("begin") int begin,
                                         @Param("end") int end,
                                         @Param("pagesize") int pagesize);

    int changeCardStatus(@Param("status") String status,
                         @Param("payid") String payid);

    int autoExpire();
    int autoReduceTerm();
    int autoOpenCard();
    int autoReduceOpenTerm();

    List<MemberPageResult> getUseableCards(@Param("userid") String userid,
                                           @Param("scheduleid") String scheduleid);

    int withdrawCard(String payid);
    List<CardOrderResult> getCardsByUser(String userid);
}