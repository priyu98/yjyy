package com.example.yjyy.service;

import com.example.yjyy.entity.Card;
import com.example.yjyy.entity.PayCard;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.CardPageResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;

public interface CardService {
    WebRestResult addCard(Card card);
    WebRestResult deleteCard(String cardid);
    WebRestResult updateCard(Card card);
    PageResult<CardPageResult> getCardList(String cardname,String cardtype,int page,int pagesize);
    WebRestResult payCard(PayCard payCard);
    WebRestResult deleteUserCard(String payid);
    WebRestResult giveCard(PayCard payCard);
    PageResult<MemberPageResult> getMemberList(String username,String teacher,String cardno,String paystatus,int page,int pagesize);
    WebRestResult changeCardStatus(String status,String payid);
    WebRestResult withdrawCard(String payid);
}
