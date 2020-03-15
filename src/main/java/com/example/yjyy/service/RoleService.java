package com.example.yjyy.service;

import com.example.yjyy.entity.Role;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.RoleResult;

public interface RoleService {
    WebRestResult addRole(Role role);
    WebRestResult deleteRole(String roleid);
    RoleResult getRoleList();
    WebRestResult updateRole(Role role);
    WebRestResult addUserRole(String userid, String roleid);
    WebRestResult updateUserRole(String userid,String roleid);
}
