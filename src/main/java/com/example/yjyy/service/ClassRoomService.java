package com.example.yjyy.service;

import com.example.yjyy.entity.ClassRoom;
import com.example.yjyy.entity.dto.ClassRoomDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.ClassRoomPageResult;

import java.util.List;

public interface ClassRoomService {
    WebRestResult addClassRoom(ClassRoom classRoom);
    WebRestResult deleteClassRoom(String classroomid);
    PageResult<ClassRoomPageResult> getClassRoomList(String venueid,String classroomname,int page,int pagesize);
    WebRestResult updateClassRoom(ClassRoomDto classRoomDto);
}
