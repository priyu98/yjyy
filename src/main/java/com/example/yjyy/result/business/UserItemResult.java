package com.example.yjyy.result.business;

import com.example.yjyy.entity.User;
import com.example.yjyy.result.WebRestResult;

import java.util.List;

public class UserItemResult extends WebRestResult {
    private User user;
    private List<CardOrderResult> cardlist;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CardOrderResult> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<CardOrderResult> cardlist) {
        this.cardlist = cardlist;
    }
}
