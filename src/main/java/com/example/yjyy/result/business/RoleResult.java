package com.example.yjyy.result.business;

import com.example.yjyy.entity.Role;
import com.example.yjyy.result.WebRestResult;

import java.util.List;

public class RoleResult extends WebRestResult {
    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
