package com.example.yjyy.result.business.PageResult;

import com.example.yjyy.result.Page;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MemberPageResult extends Page {
    private String payid;
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private String username;
    private String phone;
    private String sex;
    private String teacher;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date givetime;
    private String cardname;
    private String cardno;
    private int quota;
    private String cardstatus;

    public String getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(String cardstatus) {
        this.cardstatus = cardstatus;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getGivetime() {
        return givetime;
    }

    public void setGivetime(Date givetime) {
        this.givetime = givetime;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
