package com.example.yjyy.entity.dto;

import java.util.List;

public class CardDto {
    private String cardid;
    private List<String> venuelist;
    private List<String> courselist;

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public List<String> getVenuelist() {
        return venuelist;
    }

    public void setVenuelist(List<String> venuelist) {
        this.venuelist = venuelist;
    }

    public List<String> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<String> courselist) {
        this.courselist = courselist;
    }
}
