package com.example.yjyy.controller;

import com.example.yjyy.entity.Venue;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.VenuePageResult;
import com.example.yjyy.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("VenueController")
public class VenueController {
    @Autowired
    private VenueService venueService;

    //新增场馆
    @AppToken
    @CrossOrigin
    @RequestMapping(value = "addVenue",method = RequestMethod.POST)
    public WebRestResult addVenue(@RequestBody Venue venue){
        WebRestResult result = venueService.addVenue(venue);
        return result;
    }

    //删除场馆
    @AppToken
    @CrossOrigin
    @PostMapping("deleteVenue")
    public WebRestResult deleteVenue(String venueid){
        WebRestResult result = venueService.deleteVenue(venueid);
        return result;
    }

    //更新场馆
    @AppToken
    @CrossOrigin
    @PostMapping("updateVenue")
    public WebRestResult updateVenue(@RequestBody Venue venue){
        WebRestResult result = venueService.updateVenue(venue);
        return result;
    }

    //获取场馆列表，可用课程名进行模糊搜索
    @AppToken
    @CrossOrigin
    @RequestMapping("getVenueList")
    public PageResult<VenuePageResult> getVenueList(String venuename,int page,int pageSize){
        PageResult<VenuePageResult> result = venueService.getVenueList(venuename,page,pageSize);
        return result;
    }


}
