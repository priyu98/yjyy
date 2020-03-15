package com.example.yjyy.dao;

import com.example.yjyy.entity.Schedule;
import com.example.yjyy.result.business.PageResult.SchedulePageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String scheduleid);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String scheduleid);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    List<SchedulePageResult> getScheduleList(@Param("coursename") String coursename,
                                             @Param("starttime") String starttime,
                                             @Param("endtime") String endtime,
                                             @Param("begin") int begin,
                                             @Param("end") int end,
                                             @Param("pagesize") int pagesize);

    int cancelOrderByAdmin(String scheduleid);
}