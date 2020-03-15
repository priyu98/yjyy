package com.example.yjyy.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.example.yjyy.dao.CourseMapper;
import com.example.yjyy.entity.Course;
import com.example.yjyy.entity.dto.CourseDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.CardResult;
import com.example.yjyy.result.business.PageResult.CoursePageResult;
import com.example.yjyy.service.CourseService;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public WebRestResult addCourse(CourseDto courseDto) {
        WebRestResult result = new WebRestResult();
        Course course = new Course();
        courseDto.setCourseid(UUIDUtil.randomUUID());
        course.setCourseid(courseDto.getCourseid());
        course.setFlag("0");
        course.setCreatedate(new Date());
        course.setCoursename(courseDto.getCoursename());
        course.setCoursephoto(courseDto.getCoursephoto());
        course.setCoursecolor(courseDto.getCoursecolor());
        course.setCoursedifficulty(courseDto.getCoursedifficulty());
        course.setCoursetype(courseDto.getCoursetype());
        course.setCourseteacher(courseDto.getCourseteacher());
        course.setDuration(courseDto.getDuration());
        course.setStudentnumber(courseDto.getStudentnumber());
        course.setRemark(courseDto.getRemark());
        course.setCreateuser(courseDto.getCreateuser());
        course.setVenueid(courseDto.getVenueid());
        if(courseMapper.insert(course)==1){
            if(courseDto.getCardlist() != null && courseDto.getCardlist().size() != 0 )
                courseMapper.insertCourseCard(courseDto);
            if(courseDto.getLabellist() != null && courseDto.getLabellist().size() != 0)
                courseMapper.insertCourseLabel(courseDto);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("新增课程失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Override
    public PageResult<CoursePageResult> getCourseList(String coursename, int page, int pagesize) {
        PageResult<CoursePageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<CoursePageResult> courseList = courseMapper.getCourseList(coursename,begin,end,pagesize);
        if(courseList.size()>0){
            for(CoursePageResult coursePageResult: courseList){
                List<CardResult> cardList = courseMapper.getCardListByCourseId(coursePageResult.getCourseid());
                List<String> labelList = courseMapper.getLabelByCourseId(coursePageResult.getCourseid());
                coursePageResult.setCardlist(cardList);
                coursePageResult.setLabellist(labelList);
            }
            result.setTotal(courseList.get(0).getCnt());
            result.setPageCount(courseList.get(0).getPage());
            result.setRows(courseList);
        }
        result.setResult(WebRestResult.SUCCESS);
        result.setMessage("获取课程列表成功");
        return result;
    }

    @Override
    public WebRestResult deleteCourse(String courseid) {
        WebRestResult result = new WebRestResult();
        Course course = new Course();
        course.setCourseid(courseid);
        course.setFlag("1");
        if(courseMapper.updateByPrimaryKeySelective(course)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除课程失败");
        }
        return result;
    }

    @Override
    public WebRestResult updateCourse(CourseDto courseDto) {
        WebRestResult result = new WebRestResult();
        Course course = new Course();
        course.setCourseid(courseDto.getCourseid());
        //System.out.println(courseDto.getCourseid());
        course.setModifydate(new Date());
        course.setCoursename(courseDto.getCoursename());
        course.setCoursephoto(courseDto.getCoursephoto());
        course.setCoursecolor(courseDto.getCoursecolor());
        course.setCoursedifficulty(courseDto.getCoursedifficulty());
        course.setCoursetype(courseDto.getCoursetype());
        course.setCourseteacher(courseDto.getCourseteacher());
        course.setDuration(courseDto.getDuration());
        course.setStudentnumber(courseDto.getStudentnumber());
        course.setRemark(courseDto.getRemark());
        course.setModifyuser(courseDto.getModifyuser());
        course.setVenueid(courseDto.getVenueid());
        courseMapper.deleteCardCourse(course.getCourseid());
        courseMapper.deleteCourseLabel(course.getCourseid());
        if(courseMapper.updateByPrimaryKeySelective(course)==1){
            if(courseDto.getCardlist() != null && courseDto.getCardlist().size() != 0 )
                courseMapper.insertCourseCard(courseDto);
            if(courseDto.getLabellist() != null && courseDto.getLabellist().size() != 0)
                courseMapper.insertCourseLabel(courseDto);
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("更新课程失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
}
