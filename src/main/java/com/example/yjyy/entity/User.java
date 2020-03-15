package com.example.yjyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String userid;

    private String username;

    private String userphone;

    private String openid;

    private String password;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date userbirthday;

    private String userphoto;

    private String sex;

    private String createuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private String modifyuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifydate;

    private String address;

    private String invitecode;

    private Integer credit;

    private Integer coin;

    private String flag;

    private String venueid;

    private String employeetype;

    private String teachertype;

    private String remark;

    private String employeestatus;

    public User(String userid, String username, String userphone, String openid, String password, Date userbirthday, String userphoto, String sex, String createuser, Date createdate, String modifyuser, Date modifydate, String address, String invitecode, Integer credit, Integer coin, String flag, String venueid, String employeetype, String teachertype, String remark, String employeestatus) {
        this.userid = userid;
        this.username = username;
        this.userphone = userphone;
        this.openid = openid;
        this.password = password;
        this.userbirthday = userbirthday;
        this.userphoto = userphoto;
        this.sex = sex;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.address = address;
        this.invitecode = invitecode;
        this.credit = credit;
        this.coin = coin;
        this.flag = flag;
        this.venueid = venueid;
        this.employeetype = employeetype;
        this.teachertype = teachertype;
        this.remark = remark;
        this.employeestatus = employeestatus;
    }

    public User() {
        super();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(Date userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto == null ? null : userphoto.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode == null ? null : invitecode.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype == null ? null : employeetype.trim();
    }

    public String getTeachertype() {
        return teachertype;
    }

    public void setTeachertype(String teachertype) {
        this.teachertype = teachertype == null ? null : teachertype.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(String employeestatus) {
        this.employeestatus = employeestatus == null ? null : employeestatus.trim();
    }
}