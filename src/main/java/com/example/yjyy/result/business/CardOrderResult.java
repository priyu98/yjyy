package com.example.yjyy.result.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CardOrderResult {
    private String payid;
    private String cardname;
    private String cardno;
    private int quota;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date givetime;
    private String giveuser;
    private String cardstatus;
    private int term;

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
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

    public Date getGivetime() {
        return givetime;
    }

    public void setGivetime(Date givetime) {
        this.givetime = givetime;
    }

    public String getGiveuser() {
        return giveuser;
    }

    public void setGiveuser(String giveuser) {
        this.giveuser = giveuser;
    }

    public String getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(String cardstatus) {
        this.cardstatus = cardstatus;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
