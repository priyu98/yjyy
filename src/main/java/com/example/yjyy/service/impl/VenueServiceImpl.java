package com.example.yjyy.service.impl;

import com.example.yjyy.dao.VenueMapper;
import com.example.yjyy.entity.Venue;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.VenuePageResult;
import com.example.yjyy.service.VenueService;
import com.example.yjyy.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueMapper venueDao;

    @Override
    public WebRestResult addVenue(Venue venue) {
        WebRestResult result = new WebRestResult();
        venue.setVenueid(UUIDUtil.randomUUID());
        venue.setCreatedate(new Date());
        venue.setFlag("0");
        if(venueDao.insert(venue)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("新增场馆失败");
        }
        return result;
    }

    @Override
    public WebRestResult deleteVenue(String venueid) {
        WebRestResult result = new WebRestResult();
        if(venueDao.deleteVenue(venueid)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("删除场馆失败");
        }
        return result;
    }

    @Override
    public PageResult<VenuePageResult> getVenueList(String venuename, int page, int pagesize) {
        PageResult<VenuePageResult> result = new PageResult<>();

        int begin = (page-1) * pagesize;
        int end = pagesize;

        List<VenuePageResult> list = venueDao.getVenueList(venuename,begin,end,pagesize);
        if(list.size()>0){
            result.setPageCount(list.get(0).getPage());
            result.setTotal(list.get(0).getCnt());
            result.setRows(list);
        }
        result.setResult(PageResult.SUCCESS);
        result.setMessage("获取场馆列表成功");
        return result;
    }

    @Override
    public WebRestResult updateVenue(Venue venue){
        WebRestResult result = new WebRestResult();
        venue.setModifydate(new Date());
        if(venueDao.updateByPrimaryKeySelective(venue)==1){
            result.setResult(WebRestResult.SUCCESS);
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("更新场馆失败");
        }
        return result;
    }
}
