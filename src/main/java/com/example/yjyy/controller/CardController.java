package com.example.yjyy.controller;

import com.example.yjyy.entity.Card;
import com.example.yjyy.entity.PayCard;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.CardPageResult;
import com.example.yjyy.result.business.PageResult.MemberPageResult;
import com.example.yjyy.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CardController")
public class CardController {
    @Autowired
    private CardService cardService;

    @AppToken
    @CrossOrigin
    @PostMapping("addCard")
    public WebRestResult addCard(@RequestBody Card card){
        WebRestResult result = cardService.addCard(card);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteCard")
    public WebRestResult deleteCard(String cardid){
        WebRestResult result = cardService.deleteCard(cardid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateCard")
    public WebRestResult updateCard(@RequestBody Card card){
        WebRestResult result = cardService.updateCard(card);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getCardList")
    public PageResult<CardPageResult> getCardList(String cardname,String cardtype,int page,int pagesize){
        PageResult<CardPageResult> result = cardService.getCardList(cardname,cardtype,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("payCard")
    public WebRestResult payCard(@RequestBody PayCard payCard){
        WebRestResult result = cardService.payCard(payCard);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteUserCard")
    public WebRestResult deleteUserCard(String payid){
        WebRestResult result = cardService.deleteUserCard(payid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("giveCard")
    public WebRestResult giveCard(@RequestBody PayCard payCard){
        WebRestResult result = cardService.giveCard(payCard);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getMemberList")
    PageResult<MemberPageResult> getMemberList(String username,String teacher,String cardno,String paystatus,int page,int pagesize){
        PageResult<MemberPageResult> result = cardService.getMemberList(username,teacher,cardno,paystatus,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("changeCardStatus")
    public WebRestResult changeCardStatus(String status,String payid){
        WebRestResult result = cardService.changeCardStatus(status,payid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("withdrawCard")
    public WebRestResult withdrawCard(String payid){
        WebRestResult result = cardService.withdrawCard(payid);
        return result;
    }
}
