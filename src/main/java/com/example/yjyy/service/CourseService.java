package com.example.yjyy.service;

import com.example.yjyy.entity.dto.CourseDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.CoursePageResult;

public interface CourseService {
    WebRestResult addCourse(CourseDto courseDto);
    PageResult<CoursePageResult> getCourseList(String coursename,int page,int pagesize);
    WebRestResult deleteCourse(String courseid);
    WebRestResult updateCourse(CourseDto courseDto);
}
