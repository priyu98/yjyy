package com.example.yjyy.controller;

import com.example.yjyy.entity.ClassRoom;
import com.example.yjyy.entity.dto.ClassRoomDto;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.ClassRoomPageResult;
import com.example.yjyy.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ClassRoomController")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @AppToken
    @CrossOrigin
    @PostMapping("addClassRoom")
    public WebRestResult addClassRoom(@RequestBody ClassRoom classRoom){
        WebRestResult result = classRoomService.addClassRoom(classRoom);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteClassRoom")
    public WebRestResult deleteClassRoom(String classroomid){
        WebRestResult result = classRoomService.deleteClassRoom(classroomid);
        return  result;
    }

    @AppToken
    @CrossOrigin
    @RequestMapping("getClassRoomList")
    public PageResult<ClassRoomPageResult> getClassRoomList(String venueid,String classroomname,int page,int pagesize){
        PageResult<ClassRoomPageResult> result = classRoomService.getClassRoomList(venueid,classroomname,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateClassRoom")
    public WebRestResult updateClassRoom(@RequestBody ClassRoomDto classRoomDto){
        WebRestResult result = classRoomService.updateClassRoom(classRoomDto);
        return result;
    }
}
