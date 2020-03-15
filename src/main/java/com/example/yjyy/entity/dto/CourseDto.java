package com.example.yjyy.entity.dto;

import java.util.List;

public class CourseDto {
    private String courseid;

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    private String coursename;
    private String coursetype;
    private String coursephoto;
    private String courseteacher;
    private int duration;
    private int studentnumber;
    private String coursecolor;
    private String coursedifficulty;
    private String createuser;
    private String modifyuser;
    private String venueid;
    private String remark;
    private List<String> cardlist;

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

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
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

    public List<String> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<String> cardlist) {
        this.cardlist = cardlist;
    }

    public List<String> getLabellist() {
        return labellist;
    }

    public void setLabellist(List<String> labellist) {
        this.labellist = labellist;
    }

    private List<String> labellist;
}
