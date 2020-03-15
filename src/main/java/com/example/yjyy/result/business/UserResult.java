package com.example.yjyy.result.business;

import com.example.yjyy.entity.User;
import com.example.yjyy.result.WebRestResult;

import java.util.List;

public class UserResult extends WebRestResult {
    private User user;
    private String token;
    private String rolename;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
