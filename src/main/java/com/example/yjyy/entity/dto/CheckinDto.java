package com.example.yjyy.entity.dto;

import java.util.Date;
import java.util.List;

public class CheckinDto {
    private Date date;
    private List<String> orderlist;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<String> orderlist) {
        this.orderlist = orderlist;
    }
}
