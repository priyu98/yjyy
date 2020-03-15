package com.example.yjyy.result.business;


import com.example.yjyy.result.WebRestResult;

public class verificationResult extends WebRestResult {
    private String image;
    private String sessionid;
    private String verification;

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
