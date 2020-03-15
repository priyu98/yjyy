package com.example.yjyy.dao;

import com.example.yjyy.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    String getRoleNameByUser(String userid);

    List<Role> getRoleList();

    int deleteRole(String roleid);

    int addUserRole(@Param("userid") String userid,
                    @Param("roleid") String roleid);

    int updateUserRole(@Param("userid") String userid,
                       @Param("roleid") String roleid);
}