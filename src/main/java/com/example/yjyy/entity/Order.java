package com.example.yjyy.entity;

import java.util.Date;

public class Order {
    private String orderid;

    private String userid;

    private String scheduleid;

    private Date ordertime;

    private String updateread;

    private Date checkintime;

    private String orderstatus;

    private String payid;

    public Order(String orderid, String userid, String scheduleid, Date ordertime, String updateread, Date checkintime, String orderstatus, String payid) {
        this.orderid = orderid;
        this.userid = userid;
        this.scheduleid = scheduleid;
        this.ordertime = ordertime;
        this.updateread = updateread;
        this.checkintime = checkintime;
        this.orderstatus = orderstatus;
        this.payid = payid;
    }

    public Order() {
        super();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid == null ? null : scheduleid.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getUpdateread() {
        return updateread;
    }

    public void setUpdateread(String updateread) {
        this.updateread = updateread == null ? null : updateread.trim();
    }

    public Date getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }
}