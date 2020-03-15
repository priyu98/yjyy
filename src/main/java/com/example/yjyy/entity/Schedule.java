package com.example.yjyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Schedule {
    private String scheduleid;

    private String coursename;

    private String venueid;

    private String coursetype;

    private String courseteacher;

    private Integer duration;

    private String classroom;

    private Integer studentnumber;

    private String coursecolor;

    private String coursedifficulty;

    private BigDecimal subsidy;

    private Integer studentlimit;

    private String extraoption;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date starttime;

    private String createuser;

    private Date createdate;

    private String modifyuser;

    private Date modifydate;

    private String flag;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endtime;

    private String coursephoto;

    public String getCoursephoto() {
        return coursephoto;
    }

    public void setCoursephoto(String coursephoto) {
        this.coursephoto = coursephoto;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;

    public Schedule(String scheduleid, String coursename, String venueid, String coursetype, String courseteacher, Integer duration, String classroom, Integer studentnumber, String coursecolor, String coursedifficulty, BigDecimal subsidy, Integer studentlimit, String extraoption, Date starttime, String createuser, Date createdate, String modifyuser, Date modifydate, String flag, Date endtime) {
        this.scheduleid = scheduleid;
        this.coursename = coursename;
        this.venueid = venueid;
        this.coursetype = coursetype;
        this.courseteacher = courseteacher;
        this.duration = duration;
        this.classroom = classroom;
        this.studentnumber = studentnumber;
        this.coursecolor = coursecolor;
        this.coursedifficulty = coursedifficulty;
        this.subsidy = subsidy;
        this.studentlimit = studentlimit;
        this.extraoption = extraoption;
        this.starttime = starttime;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.flag = flag;
        this.endtime = endtime;
    }

    public Schedule() {
        super();
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid == null ? null : scheduleid.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
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

    public BigDecimal getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(BigDecimal subsidy) {
        this.subsidy = subsidy;
    }

    public Integer getStudentlimit() {
        return studentlimit;
    }

    public void setStudentlimit(Integer studentlimit) {
        this.studentlimit = studentlimit;
    }

    public String getExtraoption() {
        return extraoption;
    }

    public void setExtraoption(String extraoption) {
        this.extraoption = extraoption == null ? null : extraoption.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
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

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}