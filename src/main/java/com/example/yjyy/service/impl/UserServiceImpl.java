package com.example.yjyy.service.impl;

import com.example.yjyy.dao.CardMapper;
import com.example.yjyy.dao.PayCardMapper;
import com.example.yjyy.dao.RoleMapper;
import com.example.yjyy.dao.UserMapper;
import com.example.yjyy.entity.User;
import com.example.yjyy.entity.dto.LoginUserDto;
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

    @Autowired
    private CardMapper cardMapper;

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
        User user = userDao.loginAdmin(username, MD5Util.getMD5Info(password));//判断数据库是否存在账号密码相同的user
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
        String openid = userDao.selectByPrimaryKey(userid).getOpenid();
        if(userDao.updateByPrimaryKeySelective(user)==1){
            if(!"".equals(openid) && openid != null){
                userDao.deleteSessionKeyByOpenid(openid);
            }
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
        for(CardOrderResult cardOrderResult: list){
            cardOrderResult.setCourseList(cardMapper.getCourseListByCardId(cardOrderResult.getCardid()));
            cardOrderResult.setVenueList(cardMapper.getVenueListByCardId(cardOrderResult.getCardid()));
        }
        result.setCardlist(list);
        result.setResult(WebRestResult.SUCCESS);
        return result;
    }

    @Override
    public UserResult loginUser(LoginUserDto loginUserDto) {
        String code = loginUserDto.getCode();
        String username = loginUserDto.getUsername();
        String userphoto = loginUserDto.getUserphoto();
        String sex = loginUserDto.getSex();
        String encryptedData = loginUserDto.getEncryptedData();
        String iv = loginUserDto.getIv();
        UserResult result = new UserResult();
        String response = HttpUtils.code2Session(code);
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.has("openid")){  //根据code解析出了openid
            String openid = jsonObject.getString("openid");
            String userid = userDao.getUserByOpenid(openid);
            if(!"".equals(userid) && userid != null){    //用户已存在
                User user = userDao.selectByPrimaryKey(userid);
                String rolename = roleDao.getRoleNameByUser(user.getUserid());
                String token = JwtUtils.generateToken(user.getUserid());
                userDao.updateSessionKey(jsonObject.getString("session_key"),openid);
                result.setUser(user);
                result.setRolename(rolename);
                result.setToken(token);
                result.setResult(WebRestResult.SUCCESS);
            }
            else{         //新用户注册
                userDao.insertSessionKey(jsonObject.getString("openid"),jsonObject.getString("session_key"),null);
                User user = new User();
                userid = UUIDUtil.randomUUID();
                user.setUserid(userid);
                user.setCreatedate(new Date());
                user.setFlag("0");
                user.setOpenid(openid);
                if(!"".equals(username) && username != null){
                    user.setUsername(username);
                    user.setUserphoto(userphoto);
                    user.setSex(sex);
                }
                else{
                    user.setUsername("用户"+ Tools.getRandomNum()); //随机生成用户名
                    user.setSex("0");
                }
                if(!"".equals(encryptedData) && encryptedData!=null){
                    JSONObject data = new JSONObject(Tools.decrypt(userDao.getSessionKey(openid),iv,encryptedData));//解密加密数据
                    user.setUserphone(data.getString("phoneNumber"));
                }
                userDao.insertSelective(user);
                roleDao.addUserRole(userid,"db85e3db01e446aca8a99e4da5da42eb");//新用户分配角色
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

    @Override
    public WebRestResult updateUserPhone(LoginUserDto loginUserDto) {
        WebRestResult result = new WebRestResult();
        if(loginUserDto.getUserid()==null && "".equals(loginUserDto.getUserid())){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("userid不能为空");
        }
        else{
            User user = userDao.selectByPrimaryKey(loginUserDto.getUserid());
            JSONObject jsonObject = new JSONObject(Tools.decrypt(userDao.getSessionKey(user.getOpenid()),loginUserDto.getIv(),loginUserDto.getEncryptedData()));
            user.setUserphone(jsonObject.getString("phoneNumber"));
            if(userDao.updateByPrimaryKeySelective(user)==1){
                result.setResult(WebRestResult.SUCCESS);
            }
            else{
                result.setResult(WebRestResult.FAILURE);
                result.setMessage("更新手机号失败");
            }
        }
        return result;
    }
}
