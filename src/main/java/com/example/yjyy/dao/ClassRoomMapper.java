package com.example.yjyy.dao;

import com.example.yjyy.entity.ClassRoom;
import com.example.yjyy.result.business.PageResult.ClassRoomPageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassRoomMapper {
    int deleteByPrimaryKey(String classroomid);

    int insert(ClassRoom record);

    int insertSelective(ClassRoom record);

    ClassRoom selectByPrimaryKey(String classroomid);

    int updateByPrimaryKeySelective(ClassRoom record);

    int updateByPrimaryKey(ClassRoom record);

    List<ClassRoomPageResult> getClassRoomList(@Param("venueid") String venueid,
                                               @Param("classroomname") String classroomname,
                                               @Param("begin") int begin,
                                               @Param("end") int end,
                                               @Param("pagesize") int pagesize);
}