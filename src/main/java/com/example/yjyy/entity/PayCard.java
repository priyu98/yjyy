package com.example.yjyy.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PayCard {
    private String payid;

    private String userid;

    private String giveuser;

    private Date givetime;

    private BigDecimal payment;

    private String paystatus;

    private String cardid;

    private String cardno;

    private String cardstatus;

    private Integer term;

    private Integer openterm;

    private Integer quota;

    private Integer payflag;

    public Integer getPayflag() {
        return payflag;
    }

    public void setPayflag(Integer payflag) {
        this.payflag = payflag;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public PayCard(String payid, String userid, String giveuser, Date givetime, BigDecimal payment, String paystatus, String cardid, String cardno, String cardstatus, Integer term, Integer openterm,Integer quota) {
        this.payid = payid;
        this.userid = userid;
        this.giveuser = giveuser;
        this.givetime = givetime;
        this.payment = payment;
        this.paystatus = paystatus;
        this.cardid = cardid;
        this.cardno = cardno;
        this.cardstatus = cardstatus;
        this.term = term;
        this.openterm = openterm;
        this.quota = quota;
    }

    public PayCard() {
        super();
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getGiveuser() {
        return giveuser;
    }

    public void setGiveuser(String giveuser) {
        this.giveuser = giveuser == null ? null : giveuser.trim();
    }

    public Date getGivetime() {
        return givetime;
    }

    public void setGivetime(Date givetime) {
        this.givetime = givetime;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(String cardstatus) {
        this.cardstatus = cardstatus == null ? null : cardstatus.trim();
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getOpenterm() {
        return openterm;
    }

    public void setOpenterm(Integer openterm) {
        this.openterm = openterm;
    }
}