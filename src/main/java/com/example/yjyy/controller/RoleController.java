package com.example.yjyy.controller;

import com.example.yjyy.entity.Role;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("RoleController")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @AppToken
    @CrossOrigin
    @PostMapping("addRole")
    public WebRestResult addRole(@RequestBody Role role){
        WebRestResult result = roleService.addRole(role);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteRole")
    public WebRestResult deleteRole(String roleid){
        WebRestResult result = roleService.deleteRole(roleid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getRoleList")
    public WebRestResult getRoleList(){
        WebRestResult result = roleService.getRoleList();
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateRole")
    public WebRestResult updateRole(@RequestBody Role role){
        WebRestResult result = roleService.updateRole(role);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("addUserRole")
    public WebRestResult addUserRole(String userid,String roleid){
        WebRestResult result = roleService.addUserRole(userid,roleid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateUserRole")
    public WebRestResult updateUserRole(String userid,String roleid){
        WebRestResult result = roleService.updateUserRole(userid,roleid);
        return result;
    }
}
