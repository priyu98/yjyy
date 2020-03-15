package com.example.yjyy.service.impl;

import com.example.yjyy.dao.RoleMapper;
import com.example.yjyy.entity.Role;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.RoleResult;
import com.example.yjyy.service.RoleService;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleDao;

    @Override
    public WebRestResult addRole(Role role) {
        WebRestResult result = new WebRestResult();
        role.setCreatedate(new Date());
        role.setRoleid(UUIDUtil.randomUUID());
        role.setFlag("0");
        if(roleDao.insert(role)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("新增角色失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteRole(String roleid) {
        WebRestResult result = new WebRestResult();
        if(roleDao.deleteRole(roleid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除角色失败");
        }
        return result;
    }

    @Override
    public RoleResult getRoleList() {
        RoleResult result = new RoleResult();
        List<Role> list = roleDao.getRoleList();
        if(list.size()>0){
            result.setRoleList(list);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("获取列表失败");
        }
        return result;
    }

    @Override
    public WebRestResult updateRole(Role role) {
        WebRestResult result = new WebRestResult();
        role.setModifydate(new Date());
        if(roleDao.updateByPrimaryKeySelective(role)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("更新角色失败");
        }
        return result;
    }

    @Override
    public WebRestResult addUserRole(String userid,String roleid){
        WebRestResult result = new WebRestResult();
        if(roleDao.getRoleNameByUser(userid)!=null){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("用户已分配角色");
        }
        else if(roleDao.addUserRole(userid,roleid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("角色分配失败");
        }
        return result;
    }

    @Override
    public WebRestResult updateUserRole(String userid,String roleid){
        WebRestResult result = new WebRestResult();
        if(roleDao.updateUserRole(userid,roleid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("用户角色修改失败");
        }
        return result;
    }
}
