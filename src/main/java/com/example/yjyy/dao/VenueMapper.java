package com.example.yjyy.dao;

import com.example.yjyy.entity.Venue;
import com.example.yjyy.result.business.PageResult.VenuePageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VenueMapper {
    int deleteByPrimaryKey(String venueid);

    int insert(Venue record);

    int insertSelective(Venue record);

    Venue selectByPrimaryKey(String venueid);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);

    int deleteVenue(String venueid);

    List<VenuePageResult> getVenueList(@Param("venuename") String venuename,
                                       @Param("begin") int begin,
                                       @Param("end") int end,
                                       @Param("pagesize") int pagesize);
}