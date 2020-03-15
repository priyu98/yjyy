package com.example.yjyy.controller;

import com.example.yjyy.entity.dto.CourseDto;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.CoursePageResult;
import com.example.yjyy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CourseController")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @AppToken
    @CrossOrigin
    @PostMapping("addCourse")
    public WebRestResult addCourse(@RequestBody CourseDto courseDto){
        WebRestResult result = courseService.addCourse(courseDto);
        return result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getCourseList")
    public PageResult<CoursePageResult> getCourseList(String coursename,int page,int pagesize){
        PageResult<CoursePageResult> result = courseService.getCourseList(coursename,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteCourse")
    public WebRestResult deleteCourse(String courseid){
        WebRestResult result = courseService.deleteCourse(courseid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateCourse")
    public WebRestResult updateCourse(@RequestBody CourseDto courseDto){
        WebRestResult result = courseService.updateCourse(courseDto);
        return result;
    }
}
