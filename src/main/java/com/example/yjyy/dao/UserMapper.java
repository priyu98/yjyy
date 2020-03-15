package com.example.yjyy.dao;

import com.example.yjyy.entity.User;
import com.example.yjyy.result.business.PageResult.UserPageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loginAdmin(@Param("username") String username,
                    @Param("password") String password);

    List<UserPageResult> getAllUserList(@Param("username") String username,
                                        @Param("venueid") String venueid,
                                        @Param("roleid") String roleid,
                                        @Param("begin") int begin,
                                        @Param("end") int end,
                                        @Param("pagesize") int pagesize);

    List<UserPageResult> getEmployeeList(@Param("username") String username,
                                        @Param("venueid") String venueid,
                                        @Param("employeestatus") String employeestatus,
                                        @Param("begin") int begin,
                                        @Param("end") int end,
                                        @Param("pagesize") int pageszie);

    String getAccessToken();
    int updateAccessToken(String access_token);
    String getUserByOpenid(String openid);
    int insertSessionKey(@Param("openid") String openid,
                         @Param("session_key") String session_key,
                         @Param("unionid") String unionid);
    String getSessionKey(String openid);
}