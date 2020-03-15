package com.example.yjyy.result.business.PageResult;

import com.example.yjyy.result.Page;
import com.example.yjyy.result.business.CardResult;

import java.util.List;

public class CoursePageResult extends Page {
    private String courseid;
    private String coursename;
    private String coursetype;
    private String coursephoto;
    private String courseteacher;
    private int duration;
    private int studentnumber;
    private String coursecolor;
    private String coursedifficulty;
    private String venueid;
    private String remark;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getCoursephoto() {
        return coursephoto;
    }

    public void setCoursephoto(String coursephoto) {
        this.coursephoto = coursephoto;
    }

    public String getCourseteacher() {
        return courseteacher;
    }

    public void setCourseteacher(String courseteacher) {
        this.courseteacher = courseteacher;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(int studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getCoursecolor() {
        return coursecolor;
    }

    public void setCoursecolor(String coursecolor) {
        this.coursecolor = coursecolor;
    }

    public String getCoursedifficulty() {
        return coursedifficulty;
    }

    public void setCoursedifficulty(String coursedifficulty) {
        this.coursedifficulty = coursedifficulty;
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public List<CardResult> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<CardResult> cardlist) {
        this.cardlist = cardlist;
    }

    public List<String> getLabellist() {
        return labellist;
    }

    public void setLabellist(List<String> labellist) {
        this.labellist = labellist;
    }

    private List<CardResult> cardlist;
    private List<String> labellist;
}
