package com.example.yjyy.entity.dto;

public class UserDto {
    private String userid;
    private int begin;
    private int end;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public UserDto(String userid, int begin, int end, int pagesize) {
        this.userid = userid;
        this.begin = begin;
        this.end = end;
        this.pagesize = pagesize;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    private int pagesize;
}
