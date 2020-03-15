package com.example.yjyy.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Card {
    private String cardid;

    private String cardname;

    private String cardtype;

    private BigDecimal price;

    private Integer term;

    private Integer quota;

    private Integer credit;

    private String availabletime;

    private Integer autorenewtime;

    private Integer moststudent;

    private String createuser;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    private String flag;

    private List<String> venueList;
    private List<String> courseList;

    public List<String> getVenueList() {
        return venueList;
    }

    public void setVenueList(List<String> venueList) {
        this.venueList = venueList;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    public Card(String cardid, String cardname, String cardtype, BigDecimal price, Integer term, Integer quota, Integer credit, String availabletime, Integer autorenewtime, Integer moststudent, String createuser, Date createdate, String modifyuser, Date modifydate, String flag) {
        this.cardid = cardid;
        this.cardname = cardname;
        this.cardtype = cardtype;
        this.price = price;
        this.term = term;
        this.quota = quota;
        this.credit = credit;
        this.availabletime = availabletime;
        this.autorenewtime = autorenewtime;
        this.moststudent = moststudent;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.flag = flag;
    }

    public Card() {
        super();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname == null ? null : cardname.trim();
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getAvailabletime() {
        return availabletime;
    }

    public void setAvailabletime(String availabletime) {
        this.availabletime = availabletime == null ? null : availabletime.trim();
    }

    public Integer getAutorenewtime() {
        return autorenewtime;
    }

    public void setAutorenewtime(Integer autorenewtime) {
        this.autorenewtime = autorenewtime;
    }

    public Integer getMoststudent() {
        return moststudent;
    }

    public void setMoststudent(Integer moststudent) {
        this.moststudent = moststudent;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser == null ? null : modifyuser.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}