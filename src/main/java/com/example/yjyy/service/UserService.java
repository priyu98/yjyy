package com.example.yjyy.service;

import com.example.yjyy.entity.User;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.UserPageResult;
import com.example.yjyy.result.business.UserItemResult;
import com.example.yjyy.result.business.UserResult;

public interface UserService {
    UserResult getUsername(String userid);
    UserResult loginAdmin(String username,String password);
    PageResult<UserPageResult> getAllUserList(String username,String venueid,String roleid,int page,int pagesize);
    PageResult<UserPageResult> getEmployeeList(String username,String venueid,String employeestatus,int page,int pagesize);
    WebRestResult updateUser(User user);
    WebRestResult deleteUser(String userid);
    WebRestResult dismissionUser(String userid);
    UserItemResult getUserItem(String userid);
    UserResult loginUser(String code,String username,String userphoto,String sex,String encryptedData,String iv);
}
