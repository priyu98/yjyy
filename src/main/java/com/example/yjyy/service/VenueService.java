package com.example.yjyy.service;

import com.example.yjyy.entity.Venue;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.VenuePageResult;

public interface VenueService {
    WebRestResult addVenue(Venue venue);
    WebRestResult deleteVenue(String venueid);
    PageResult<VenuePageResult> getVenueList(String venuename,int page,int pagesize);
    WebRestResult updateVenue(Venue venue);
}
