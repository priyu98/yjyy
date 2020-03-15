package com.example.yjyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ClassRoom {
    private String classroomid;

    private String classroomname;

    private String venueid;

    private Integer studentnumber;

    private String createuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private String modifyuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifydate;

    private String flag;

    public ClassRoom(String classroomid, String classroomname, String venueid, Integer studentnumber, String createuser, Date createdate, String modifyuser, Date modifydate, String flag) {
        this.classroomid = classroomid;
        this.classroomname = classroomname;
        this.venueid = venueid;
        this.studentnumber = studentnumber;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.flag = flag;
    }

    public ClassRoom() {
        super();
    }

    public String getClassroomid() {
        return classroomid;
    }

    public void setClassroomid(String classroomid) {
        this.classroomid = classroomid == null ? null : classroomid.trim();
    }

    public String getClassroomname() {
        return classroomname;
    }

    public void setClassroomname(String classroomname) {
        this.classroomname = classroomname == null ? null : classroomname.trim();
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    public Integer getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(Integer studentnumber) {
        this.studentnumber = studentnumber;
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