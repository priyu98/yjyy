package com.example.yjyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Venue {
    private String venueid;

    private String venuename;

    private String contact;

    private String venuetype;

    private String mobile;

    private String phone;

    private String area;

    private String address;

    private String qrcode;

    private String remark;

    private String mapmark;

    private String createuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    private String modifyuser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifydate;

    private String flag;

    public Venue(String venueid, String venuename, String contact, String venuetype, String mobile, String phone, String area, String address, String qrcode, String remark, String mapmark, String createuser, Date createdate, String modifyuser, Date modifydate, String flag) {
        this.venueid = venueid;
        this.venuename = venuename;
        this.contact = contact;
        this.venuetype = venuetype;
        this.mobile = mobile;
        this.phone = phone;
        this.area = area;
        this.address = address;
        this.qrcode = qrcode;
        this.remark = remark;
        this.mapmark = mapmark;
        this.createuser = createuser;
        this.createdate = createdate;
        this.modifyuser = modifyuser;
        this.modifydate = modifydate;
        this.flag = flag;
    }

    public Venue() {
        super();
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    public String getVenuename() {
        return venuename;
    }

    public void setVenuename(String venuename) {
        this.venuename = venuename == null ? null : venuename.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getVenuetype() {
        return venuetype;
    }

    public void setVenuetype(String venuetype) {
        this.venuetype = venuetype == null ? null : venuetype.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMapmark() {
        return mapmark;
    }

    public void setMapmark(String mapmark) {
        this.mapmark = mapmark == null ? null : mapmark.trim();
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