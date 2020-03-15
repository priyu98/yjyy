package com.example.yjyy.entity;

import java.util.Date;

public class Course {
    private String courseid;

    private String coursename;

    private String coursetype;

    private String courseteacher;

    private String coursephoto;

    private Integer duration;

    private Integer studentnumber;

    private String coursecolor;

    private String coursedifficulty;

    private String createuser;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    private String venueid;

    private String flag;

    private String remark;

    public Course(String courseid, String coursename, String coursetype, String courseteacher, String coursephoto, Integer duration, Integer studentnumber, String coursecolor, String coursedifficulty, String createuser, Date createdate, String modifyuser, Date modifydate, String venueid, String flag, String remark) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.coursetype = coursetype;
        this.courseteacher = courseteacher;
        this.coursephoto = coursephoto;
        this.duration = duration;
        this.studentnumber = studentnumber;
        this.coursecolor = coursecolor;
        this.coursedifficulty = coursedifficulty;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.venueid = venueid;
        this.flag = flag;
        this.remark = remark;
    }

    public Course() {
        super();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype == null ? null : coursetype.trim();
    }

    public String getCourseteacher() {
        return courseteacher;
    }

    public void setCourseteacher(String courseteacher) {
        this.courseteacher = courseteacher == null ? null : courseteacher.trim();
    }

    public String getCoursephoto() {
        return coursephoto;
    }

    public void setCoursephoto(String coursephoto) {
        this.coursephoto = coursephoto == null ? null : coursephoto.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(Integer studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getCoursecolor() {
        return coursecolor;
    }

    public void setCoursecolor(String coursecolor) {
        this.coursecolor = coursecolor == null ? null : coursecolor.trim();
    }

    public String getCoursedifficulty() {
        return coursedifficulty;
    }

    public void setCoursedifficulty(String coursedifficulty) {
        this.coursedifficulty = coursedifficulty == null ? null : coursedifficulty.trim();
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

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}