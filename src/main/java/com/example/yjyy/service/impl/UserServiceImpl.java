package com.example.yjyy.service.impl;

import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.dao.RoleMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.User;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.CardOrderResult;
import com.example.yjyy.result.business.PageResult.UserPageResult;
import com.example.yjyy.result.business.UserItemResult;
import com.example.yjyy.result.business.UserResult;
import com.example.yjyy.service.UserService;
import com.example.yjyy.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private PayCardMapper payCardMapper;

    @Override
    public UserResult getUsername(String userid) {
        UserResult result = new UserResult();
        result.setUser(userDao.selectByPrimaryKey(userid));
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public UserResult loginAdmin(String username, String password) {
        UserResult result = new UserResult();
        User user = userDao.loginAdmin(username, MD5Util.getMD5Info(password));
        if(user != null && roleDao.getRoleNameByUser(user.getUserid()).contains("管理员")){
            String rolename = roleDao.getRoleNameByUser(user.getUserid());
            String token = JwtUtils.generateToken(user.getUserid());
            result.setUser(user);
            result.setRolename(rolename);
            result.setToken(token);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @Override
    public PageResult<UserPageResult> getAllUserList(String username, String venueid, String roleid, int page, int pagesize) {
        PageResult<UserPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<UserPageResult> list = userDao.getAllUserList(username,venueid,roleid,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public PageResult<UserPageResult> getEmployeeList(String username, String venueid, String employeestatus, int page, int pagesize) {
        PageResult<UserPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<UserPageResult> list = userDao.getEmployeeList(username,venueid,employeestatus,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public WebRestResult updateUser(User user) {
        WebRestResult result = new WebRestResult();
        user.setModifydate(new Date());
        if(userDao.updateByPrimaryKeySelective(user)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("修改用户失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteUser(String userid) {
        WebRestResult result = new WebRestResult();
        User user = new User();
        user.setUserid(userid);
        user.setFlag("1");
        if(userDao.updateByPrimaryKeySelective(user)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除用户失败");
        }
        return result;
    }

    @Override
    public WebRestResult dismissionUser(String userid) {
        WebRestResult result = new WebRestResult();
        User user = new User();
        user.setUserid(userid);
        user.setEmployeestatus("1");
        if(userDao.updateByPrimaryKeySelective(user)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("员工离职修改失败");
        }
        return result;
    }

    @Override
    public UserItemResult getUserItem(String userid) {
        UserItemResult result = new UserItemResult();
        User user = userDao.selectByPrimaryKey(userid);
        if(user!=null)
            result.setUser(user);
        List<CardOrderResult> list = payCardMapper.getCardsByUser(userid);
        result.setCardlist(list);
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public UserResult loginUser(String code, String username, String userphoto, String sex, String encryptedData, String iv) {
        UserResult result = new UserResult();
        String response = HttpUtils.code2Session(code);
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.getString("errcode").equals("0")){
            String openid = jsonObject.getString("openid");
            String userid = userDao.getUserByOpenid(openid);
            if(!"".equals(userid) && userid != null){
                User user = userDao.selectByPrimaryKey(userid);
                String rolename = roleDao.getRoleNameByUser(user.getUserid());
                String token = JwtUtils.generateToken(user.getUserid());
                result.setUser(user);
                result.setRolename(rolename);
                result.setToken(token);
                result.setResult(WebRestResult.SUCCESS);
            }
            else{
                userDao.insertSessionKey(jsonObject.getString("openid"),jsonObject.getString("session_key"),jsonObject.getString("unionid"));
                User user = new User();
                userid = UUIDUtil.randomUUID();
                user.setUserid(userid);
                user.setCreatedate(new Date());
                user.setFlag("0");
                user.setOpenid(openid);
                if(!"".equals(username) && username != null){
                    user.setUsername(username);
                    user.setUserphone(userphoto);
                    user.setSex(sex);
                }
                else{
                    user.setUsername("用户"+ Tools.getRandomNum());
                    user.setSex("0");
                }
                if(!"".equals(encryptedData) && encryptedData!=null){
                    JSONObject data = new JSONObject(Tools.decrypt(userDao.getSessionKey(openid),iv,encryptedData));
                    user.setUserphone(data.getString("phoneNumber"));
                }
                userDao.insertSelective(user);
                roleDao.addUserRole(userid,"db85e3db01e446aca8a99e4da5da42eb");
                String rolename = roleDao.getRoleNameByUser(user.getUserid());
                String token = JwtUtils.generateToken(user.getUserid());
                result.setUser(user);
                result.setRolename(rolename);
                result.setToken(token);
                result.setResult(WebRestResult.SUCCESS);
            }
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage(jsonObject.getString("errmsg"));
        }
        return result;
    }
}
