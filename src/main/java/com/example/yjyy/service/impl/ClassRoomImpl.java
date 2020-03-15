package com.example.yjyy.service.impl;

import com.example.yjyy.dao.ClassRoomMapper;
import com.example.yjyy.entity.ClassRoom;
import com.example.yjyy.entity.dto.ClassRoomDto;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.ClassRoomPageResult;
import com.example.yjyy.service.ClassRoomService;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClassRoomImpl implements ClassRoomService {
    @Autowired
    private ClassRoomMapper classRoomDao;

    @Override
    public WebRestResult addClassRoom(ClassRoom classRoom) {
        WebRestResult result = new WebRestResult();
        classRoom.setClassroomid(UUIDUtil.randomUUID());
        classRoom.setCreatedate(new Date());
        classRoom.setFlag("0");
        if(classRoomDao.insert(classRoom)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("新增教室失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteClassRoom(String classroomid) {
        WebRestResult result = new WebRestResult();
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassroomid(classroomid);
        classRoom.setFlag("1");
        if(classRoomDao.updateByPrimaryKeySelective(classRoom)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除教室失败");
        }
        return result;
    }

    @Override
    public PageResult<ClassRoomPageResult> getClassRoomList(String venueid, String classroomname, int page, int pagesize) {
        PageResult<ClassRoomPageResult> result = new PageResult<>();
        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<ClassRoomPageResult> list = classRoomDao.getClassRoomList(venueid,classroomname,begin,end,pagesize);
        if(list.size()>0){
            result.setTotal(list.get(0).getCnt());
            result.setPageCount(list.get(0).getPage());
            result.setRows(list);
        }
        result.setResult(PageResult.SUCCESS);
        result.setMessage("获取教室列表成功");
        return result;
    }

    @Override
    public WebRestResult updateClassRoom(ClassRoomDto classRoomDto) {
        WebRestResult result = new WebRestResult();
        List<ClassRoom> classRoomList = classRoomDto.getClassRoomList();
        if(classRoomList != null && classRoomList.size() != 0) {
            for(ClassRoom classRoom: classRoomList) {
                classRoom.setModifydate(new Date());
                if (classRoomDao.updateByPrimaryKeySelective(classRoom) == 1) {
                    continue;
                } else {
                    result.setResult(WebRestResult.FAILURE);
                    result.setMessage("更新教室失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
            result.setResult(WebRestResult.SUCCESS);
        }
        return result;
    }
}
