package com.example.yjyy.result.business.PageResult;

import com.example.yjyy.entity.Course;
import com.example.yjyy.entity.Venue;
import com.example.yjyy.result.Page;

import java.util.List;

public class CardPageResult extends Page {
    private String cardid;
    private String cardname;
    private String cardtype;
    private float price;
    private int term;
    private int quota;
    private List<Venue> venueList;
    private List<Course> courseList;

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getQuota() {
        return quota;
    }

    public List<Venue> getVenueList() {
        return venueList;
    }

    public void setVenueList(List<Venue> venueList) {
        this.venueList = venueList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

}
