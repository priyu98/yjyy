package com.example.yjyy.dao;

import com.example.yjyy.entity.Course;
import com.example.yjyy.entity.dto.CourseDto;
import com.example.yjyy.result.business.CardResult;
import com.example.yjyy.result.business.PageResult.CoursePageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int insertCourseCard(CourseDto courseDto);

    int insertCourseLabel(CourseDto courseDto);

    List<CoursePageResult> getCourseList(@Param("coursename") String coursename,
                                         @Param("begin") int begin,
                                         @Param("end") int end,
                                         @Param("pagesize") int pagesize);

    List<CardResult> getCardListByCourseId(String courseid);

    List<String> getLabelByCourseId(String courseid);

    int deleteCardCourse(String courseid);
    int deleteCourseLabel(String courseid);
}